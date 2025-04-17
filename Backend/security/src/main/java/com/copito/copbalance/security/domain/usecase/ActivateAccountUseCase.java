package com.copito.copbalance.security.domain.usecase;

import jakarta.mail.MessagingException;

public interface ActivateAccountUseCase {
    void activate(String random);
}
