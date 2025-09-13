package com.luq.picpay_backend_challenge.controllers;

import com.luq.picpay_backend_challenge.dto.request.TransactionRequest;
import com.luq.picpay_backend_challenge.dto.response.TransactionResponse;
import com.luq.picpay_backend_challenge.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    protected final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransactionResponse> transferToUser(@RequestBody TransactionRequest request){
        TransactionResponse response = transactionService.transfer(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/transfer")
    public ResponseEntity<List<TransactionResponse>> getTransactions(){
        List<TransactionResponse> list = transactionService.getAll();
        return ResponseEntity.ok().body(list);
    }
}
