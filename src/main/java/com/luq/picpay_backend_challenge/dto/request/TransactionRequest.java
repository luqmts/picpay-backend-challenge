package com.luq.picpay_backend_challenge.dto.request;

import java.math.BigDecimal;

public record TransactionRequest(
    BigDecimal amount,
    Integer payerId,
    Integer payeeId
) {}
