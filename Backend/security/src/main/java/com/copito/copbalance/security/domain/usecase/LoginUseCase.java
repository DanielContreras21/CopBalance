package com.copito.copbalance.security.domain.usecase;

import com.copito.copbalance.security.application.dto.request.LoginRequest;

public interface LoginUseCase {
    String login(LoginRequest request);
}
