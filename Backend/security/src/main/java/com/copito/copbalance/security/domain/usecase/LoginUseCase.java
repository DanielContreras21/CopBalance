package com.copito.copbalance.security.domain.usecase;

import com.copito.copbalance.security.domain.model.dto.request.LoginRequest;

public interface LoginUseCase {
    String login(LoginRequest request);
}
