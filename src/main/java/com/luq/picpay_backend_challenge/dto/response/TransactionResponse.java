package com.luq.picpay_backend_challenge.dto.response;

import com.luq.picpay_backend_challenge.domain.TransactionType;
import com.luq.picpay_backend_challenge.domain.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponse (
    Integer id,
    TransactionType transactionType,
    User payer,
    User payee,
    BigDecimal amount,
    LocalDateTime created,
    LocalDateTime modified
){}
