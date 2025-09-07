package com.copito.copbalance.security.domain.usecase.account;

import com.copito.copbalance.security.application.dto.request.AccountExistRequest;

public interface AccountExistUseCase {
    void exist(AccountExistRequest request);
}
