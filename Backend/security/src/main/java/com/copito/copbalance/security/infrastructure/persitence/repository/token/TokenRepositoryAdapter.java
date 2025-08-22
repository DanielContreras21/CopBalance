package com.copito.copbalance.security.infrastructure.persitence.repository.token;

import com.copito.copbalance.security.domain.model.entity.Token;
import com.copito.copbalance.security.domain.repository.TokenRepositoryPort;
import com.copito.copbalance.security.infrastructure.persitence.entity.TokenEntity;
import com.copito.copbalance.security.infrastructure.persitence.repository.account.AccountRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TokenRepositoryAdapter implements TokenRepositoryPort {
    private final TokenJpaRepository tokenJpaRepository;
    private final TokenMapper tokenMapper;

    @Override
    public Optional<Token> findById(Long id) {
        if (id == null){
            throw new IllegalArgumentException("El id no puede ser null");
        }
        return tokenJpaRepository.findById(id).map(tokenMapper::toDomain);
    }

    @Override
    public Token save(Token token) {
        TokenEntity entity = tokenMapper.toEntity(token);
        return tokenMapper.toDomain(tokenJpaRepository.save(entity));
    }

    @Override
    public Optional<Token> findByRandom(String random) {
        if (random == null){
            throw new IllegalArgumentException("El random no puede ser null");
        }
        return tokenJpaRepository.findByRandom(random).map(tokenMapper::toDomain);
    }
}
