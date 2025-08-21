package com.copito.copbalance.security.domain.usecase;

import com.copito.copbalance.security.application.dto.request.AccountRequest;

public interface UpdateAccountUseCase {
    void update(String id, AccountRequest request);
}
