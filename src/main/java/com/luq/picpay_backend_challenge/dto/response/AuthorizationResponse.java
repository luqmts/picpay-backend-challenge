package com.luq.picpay_backend_challenge.dto.response;

public record AuthorizationResponse(
    String status,
    Data data
){
    public record Data(
        boolean authorization
    ){}
}
