package com.copito.copbalance.security.domain.repository;

import com.copito.copbalance.security.domain.model.entity.Token;

import java.util.Optional;

public interface TokenRepositoryPort {
    Optional<Token> findById(Long id);
    Token save (Token token);
    Optional<Token> findByRandom(String random);
}
