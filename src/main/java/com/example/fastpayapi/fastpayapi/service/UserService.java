package com.example.fastpayapi.fastpayapi.service;

import com.example.fastpayapi.fastpayapi.domain.User.User;
import com.example.fastpayapi.fastpayapi.domain.User.UserType;
import com.example.fastpayapi.fastpayapi.dto.UserDTO;
import com.example.fastpayapi.fastpayapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User saveUser(User user) {
        return repository.save(user);
    }

    public User createUser(UserDTO userDTO) {
        User newUser = new User(userDTO);
        return this.saveUser(newUser);
    }

    public List<User> findUsers() {
        return repository.findAll();
    }

    public User findUserById(Long id) throws Exception {
        return repository.findUserById(id)
                .orElseThrow(() -> new Exception("User nor found."));
    }

    public void validateTransaction(User payer, BigDecimal value) throws Exception {
        if(payer.getUserType() == UserType.MERCHANT) {
            throw new Exception("Only commom user can do transactions.");
        }

        if(payer.getAccountBalance().compareTo(value) < 0) {
            throw new Exception("Payer do not have enough funds to do this transaction.");
        }
    }
}
