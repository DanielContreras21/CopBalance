package com.copito.copbalance.mail.domain.usecase;

import com.copito.copbalance.mail.application.dto.request.EmailRequest;
import jakarta.mail.MessagingException;

public interface EmailSender {
    void sendMail(EmailRequest emailRequest) throws MessagingException;
}
