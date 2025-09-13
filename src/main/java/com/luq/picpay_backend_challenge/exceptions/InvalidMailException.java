package com.luq.picpay_backend_challenge.exceptions;

public class InvalidMailException extends RuntimeException {
    public InvalidMailException(String message) {
        super(message);
    }
}
