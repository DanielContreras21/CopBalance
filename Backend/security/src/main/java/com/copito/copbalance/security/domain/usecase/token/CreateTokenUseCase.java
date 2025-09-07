package com.copito.copbalance.security.domain.usecase.token;

import com.copito.copbalance.security.domain.model.enums.TypeEnum;

public interface CreateTokenUseCase {
    void create(String id, TypeEnum type);
}
