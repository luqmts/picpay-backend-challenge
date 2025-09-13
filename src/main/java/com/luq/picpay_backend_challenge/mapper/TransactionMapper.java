package com.luq.picpay_backend_challenge.mapper;

import com.luq.picpay_backend_challenge.domain.Transaction;
import com.luq.picpay_backend_challenge.dto.response.TransactionResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionMapper {
    public TransactionResponse toDTO(Transaction entity){
        return new TransactionResponse(
            entity.getId(),
            entity.getTransactionType(),
            entity.getPayer(),
            entity.getPayee(),
            entity.getCreated(),
            entity.getModified()
        );
    }

    public List<TransactionResponse> toDTOList(List<Transaction> list){
        return list.stream().map(this::toDTO).toList();
    }
}
