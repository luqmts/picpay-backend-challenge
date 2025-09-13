package com.luq.picpay_backend_challenge.dto.request;

import java.math.BigDecimal;

public record UserRequest(
    String fullName,
    String userType,
    String document,
    String mail,
    String password,
    String confirmPassword,
    BigDecimal balance
){}
