package com.example.fastpayapi.fastpayapi.dto;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value, Long payerId, Long payeeId) {
}
