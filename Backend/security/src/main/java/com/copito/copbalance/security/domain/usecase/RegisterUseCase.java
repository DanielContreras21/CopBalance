package com.copito.copbalance.security.domain.usecase;

import com.copito.copbalance.security.domain.model.dto.request.RegisterRequest;
import com.copito.copbalance.security.domain.model.dto.response.RegisterResponse;

public interface RegisterUseCase {
    RegisterResponse register(RegisterRequest request);
}
