package com.luq.picpay_backend_challenge.dto.response;

import com.luq.picpay_backend_challenge.domain.UserType;

public record UserResponse(
    Integer id,
    UserType userType,
    String fullName,
    String document,
    String mail,
    String password
) {}
