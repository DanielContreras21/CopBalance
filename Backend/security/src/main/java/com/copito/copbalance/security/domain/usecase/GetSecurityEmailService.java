package com.copito.copbalance.security.domain.usecase;

import com.copito.copbalance.security.application.dto.response.AccountEmail;

public interface GetSecurityEmailService {
    AccountEmail getEmail(String id);
}
