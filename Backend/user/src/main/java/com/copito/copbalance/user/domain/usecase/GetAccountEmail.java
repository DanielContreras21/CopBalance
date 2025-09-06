package com.copito.copbalance.user.domain.usecase;

import com.copito.copbalance.user.infrastructure.http.response.AccountEmail;

public interface GetAccountEmail {
    AccountEmail getAccountEmail(String id);
}
