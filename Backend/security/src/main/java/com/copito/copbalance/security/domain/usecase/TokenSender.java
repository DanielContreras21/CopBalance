package com.copito.copbalance.security.domain.usecase;

import com.copito.copbalance.security.domain.model.enums.TypeEnum;

public interface TokenSender {
    void send(String id, TypeEnum type, String subject, String template);
}
