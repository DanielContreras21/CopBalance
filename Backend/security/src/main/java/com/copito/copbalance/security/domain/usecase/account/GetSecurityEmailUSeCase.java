package com.copito.copbalance.security.domain.usecase.account;

import com.copito.copbalance.security.application.dto.response.AccountEmail;

public interface GetSecurityEmailUSeCase {
    AccountEmail getEmail(String id);
}
