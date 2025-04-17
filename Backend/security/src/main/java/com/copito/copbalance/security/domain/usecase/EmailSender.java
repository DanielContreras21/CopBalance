package com.copito.copbalance.security.domain.usecase;

import jakarta.mail.MessagingException;

import java.util.Map;

public interface EmailSender {
    void sendMail(String to, String subject, String template, Map<String, Object> variables) throws MessagingException;
}
