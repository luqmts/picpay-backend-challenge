package com.luq.picpay_backend_challenge.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luq.picpay_backend_challenge.dto.request.MailNotificationRequest;
import com.luq.picpay_backend_challenge.dto.response.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    public void mailNotify(MailNotificationRequest request){
        ObjectMapper mapper = new ObjectMapper();
        try{
            String message = mapper.writeValueAsString(request);
            kafkaTemplate.send("notify-mail-topic", message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public MailNotificationRequest createMailRequest(TransactionResponse transactionResponse){
        return new MailNotificationRequest(
            transactionResponse.payee().getMail(),
            String.format(
                "You received a transaction from %s",
                transactionResponse.payer().getFullName()
            ),
            String.format(
                "%s transferred $%s to you on %tD",
                transactionResponse.payer().getFullName(),
                transactionResponse.amount().toString(),
                transactionResponse.created()
            )
        );
    }
}
