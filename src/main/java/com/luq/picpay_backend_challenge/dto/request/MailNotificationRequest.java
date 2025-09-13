package com.luq.picpay_backend_challenge.dto.request;

public record MailNotificationRequest(
    String to,
    String subject,
    String mailBody
) {}
