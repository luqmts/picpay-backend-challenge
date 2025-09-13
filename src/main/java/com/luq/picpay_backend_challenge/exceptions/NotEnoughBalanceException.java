package com.luq.picpay_backend_challenge.exceptions;

public class NotEnoughBalanceException extends RuntimeException {
    public NotEnoughBalanceException(String message) {
        super(message);
    }
}
