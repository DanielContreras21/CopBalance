package com.copito.copbalance.security.domain.usecase.account;

import com.copito.copbalance.security.application.dto.request.LoginRequest;

public interface LoginUseCase {
    String login(LoginRequest request);
}
