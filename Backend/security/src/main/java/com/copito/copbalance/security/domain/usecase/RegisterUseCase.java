package com.copito.copbalance.security.domain.usecase;

import com.copito.copbalance.security.application.dto.request.RegisterRequest;
import com.copito.copbalance.security.application.dto.response.RegisterResponse;

public interface RegisterUseCase {
    RegisterResponse register(RegisterRequest request);
}
