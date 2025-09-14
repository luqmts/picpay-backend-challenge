package com.luq.picpay_backend_challenge.dto.response;

import com.luq.picpay_backend_challenge.domain.UserType;

import java.math.BigDecimal;

public record UserResponse(
    Integer id,
    String fullName,
    UserType userType,
    String document,
    String mail,
    BigDecimal balance
) {}
