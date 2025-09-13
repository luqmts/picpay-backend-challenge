package com.luq.picpay_backend_challenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorHandling {
    Map<String, String> errors = new HashMap<>();

    @ExceptionHandler(PasswordDontMatchException.class)
    public ResponseEntity<Map<String, String>> exceptionFound(PasswordDontMatchException ex){
        errors.put("error: ", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(InvalidDocumentException.class)
    public ResponseEntity<Map<String, String>> exceptionFound(InvalidDocumentException ex){
        errors.put("error: ", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(InvalidMailException.class)
    public ResponseEntity<Map<String, String>> exceptionFound(InvalidMailException ex){
        errors.put("error: ", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(NotEnoughBalanceException.class)
    public ResponseEntity<Map<String, String>> exceptionFound(NotEnoughBalanceException ex){
        errors.put("error: ", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(InvalidTransactionTypeException.class)
    public ResponseEntity<Map<String, String>> exceptionFound(InvalidTransactionTypeException ex){
        errors.put("error: ", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Map<String, String>> exceptionFound(UnauthorizedException ex){
        errors.put("error: ", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, String>> exceptionFound(NotFoundException ex){
        errors.put("error: ", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }
}
