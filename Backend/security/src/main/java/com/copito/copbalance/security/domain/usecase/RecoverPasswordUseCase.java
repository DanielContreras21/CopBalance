package com.copito.copbalance.security.domain.usecase;

import com.copito.copbalance.security.domain.model.dto.request.RecoverPasswordRequest;

public interface RecoverPasswordUseCase {
    void updatePassword(String id, RecoverPasswordRequest request);
}
