package com.example.fastpayapi.fastpayapi.domain.Transaction;

import com.example.fastpayapi.fastpayapi.domain.User.User;
import com.example.fastpayapi.fastpayapi.dto.TransactionDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transactions")
@Table(name = "transactions")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal value;
    @ManyToOne
    @JoinColumn(name="payee_id")
    private User payee;
    @ManyToOne
    @JoinColumn(name="payer_id")
    private User payer;

    private TransactionStatus status;

    private LocalDateTime date;

    public Transaction(User payer, User payee, BigDecimal value){
        this.value = value;
        this.payee = payee;
        this.payer = payer;
        this.status = TransactionStatus.PENDING;
        this.date = LocalDateTime.now();
    }
}

