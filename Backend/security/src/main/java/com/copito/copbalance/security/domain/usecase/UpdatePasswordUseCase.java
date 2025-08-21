package com.copito.copbalance.security.domain.usecase;

import com.copito.copbalance.security.application.dto.request.UpdatePasswordRequest;

public interface UpdatePasswordUseCase {
    void updatePassword(String id, UpdatePasswordRequest request);
}
