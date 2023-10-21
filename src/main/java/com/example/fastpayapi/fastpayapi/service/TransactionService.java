package com.example.fastpayapi.fastpayapi.service;

import com.example.fastpayapi.fastpayapi.domain.Transaction.Transaction;
import com.example.fastpayapi.fastpayapi.domain.Transaction.TransactionStatus;
import com.example.fastpayapi.fastpayapi.domain.User.User;
import com.example.fastpayapi.fastpayapi.domain.User.UserType;
import com.example.fastpayapi.fastpayapi.dto.TransactionDTO;
import com.example.fastpayapi.fastpayapi.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.http.HttpResponse;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    public Transaction saveTransaction(Transaction transaction) {
        return repository.save(transaction);
    }

    public  void cancelTransaction(Long transactionId) throws Exception {
        Transaction transaction = this.findTransactionById(transactionId);
        transaction.setStatus(TransactionStatus.FAILED);
        this.saveTransaction(transaction);
    }

    public void aproveTransaction(Long transactionId) throws Exception {
        Transaction transaction = this.findTransactionById(transactionId);
        transaction.setStatus(TransactionStatus.SUCCESS);
        this.saveTransaction(transaction);
    }

    public Transaction findTransactionById(Long id) throws Exception {
        return repository.findTransactionById(id)
                .orElseThrow(() -> new Exception("User nor found."));
    }

    public void startTransaction(TransactionDTO transactionDTO) throws Exception {
        User payer = this.userService.findUserById(transactionDTO.payerId());
        User payee = this.userService.findUserById(transactionDTO.payeeId());

        this.userService.validateTransaction(payer, transactionDTO.value());
        Transaction transaction = this.saveTransaction(new Transaction(payer, payee, transactionDTO.value()));

        Boolean isAuthorized = this.authorizeTransaction(payer, transaction.getValue());
        if(!isAuthorized) {
            this.cancelTransaction(transaction.getId());
            throw new Exception("Transaction not athorized.");
        }

        payer.setAccountBalance(payer.getAccountBalance().subtract(transaction.getValue()));
        payee.setAccountBalance(payee.getAccountBalance().add(transaction.getValue()));

        this.userService.saveUser(payee);
        this.userService.saveUser(payer);
        this.aproveTransaction(transaction.getId());
    }

    private boolean authorizeTransaction(User payer, BigDecimal value) throws Exception {
        ResponseEntity<Map> response = restTemplate.getForEntity(
                "https://run.mocky.io/v3/9d7f0870-ef90-4f8e-906c-5f308ccc25e2",
                Map.class
        );

        if(response.getStatusCode() == HttpStatus.OK) {
            return true;
        } else return false;
    }
}
