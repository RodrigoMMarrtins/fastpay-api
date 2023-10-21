package com.example.fastpayapi.fastpayapi.repository;

import com.example.fastpayapi.fastpayapi.domain.Transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findById(Long id);
}
