package com.luq.picpay_backend_challenge.services;

import com.luq.picpay_backend_challenge.domain.Transaction;
import com.luq.picpay_backend_challenge.domain.TransactionType;
import com.luq.picpay_backend_challenge.domain.User;
import com.luq.picpay_backend_challenge.domain.UserType;
import com.luq.picpay_backend_challenge.dto.request.TransactionRequest;
import com.luq.picpay_backend_challenge.dto.response.AuthorizationResponse;
import com.luq.picpay_backend_challenge.dto.response.TransactionResponse;
import com.luq.picpay_backend_challenge.exceptions.InvalidTransactionTypeException;
import com.luq.picpay_backend_challenge.exceptions.NotEnoughBalanceException;
import com.luq.picpay_backend_challenge.exceptions.NotFoundException;
import com.luq.picpay_backend_challenge.exceptions.UnauthorizedException;
import com.luq.picpay_backend_challenge.mapper.TransactionMapper;
import com.luq.picpay_backend_challenge.repositories.TransactionRepository;
import com.luq.picpay_backend_challenge.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private TransactionMapper transactionMapper;

    @Transactional
    public TransactionResponse transfer(TransactionRequest request){
        User payer = userRepository.findById(request.payerId()).orElseThrow(() -> new NotFoundException("Payer not found"));
        User payee = userRepository.findById(request.payeeId()).orElseThrow(() -> new NotFoundException("Payee not found"));

        if(validateTransaction(payer, request.amount())){
            payer.setBalance(payer.getBalance().subtract(request.amount()));
            payee.setBalance(payee.getBalance().add(request.amount()));
        }

        userRepository.saveAll(List.of(payer, payee));
        Transaction transaction = transactionRepository.save(new Transaction(
            (payee.getUserType() == UserType.Common) ? TransactionType.UserToUser : TransactionType.UserToMerchant,
            payer,
            payee,
            LocalDateTime.now(),
            LocalDateTime.now()
        ));

        return transactionMapper.toDTO(transaction);
    }

    private boolean validateTransaction(User payer, BigDecimal amount){
        if(payer.getUserType().equals(UserType.Merchant))
            throw new InvalidTransactionTypeException("Only common users can transfer to another user");

        if(payer.getBalance().compareTo(amount) < 0)
            throw new NotEnoughBalanceException("Not enough balance to transfer");

        AuthorizationResponse authorizationResponse = authorizationService.authorize();
        if(authorizationResponse.status().equals("fail") || !authorizationResponse.data().authorization())
            throw new UnauthorizedException("Unauthorized to transfer");

        return true;
    }

    public List<TransactionResponse> getAll(){
        List<Transaction> transactionList = transactionRepository.findAll();
        return transactionMapper.toDTOList(transactionList);
    }
}
