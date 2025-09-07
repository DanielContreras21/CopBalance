package com.copito.copbalance.security.domain.usecase.account;

import com.copito.copbalance.security.application.dto.request.RecoverPasswordRequest;

public interface RecoverPasswordUseCase {
    void updatePassword(String id, RecoverPasswordRequest request);
}
