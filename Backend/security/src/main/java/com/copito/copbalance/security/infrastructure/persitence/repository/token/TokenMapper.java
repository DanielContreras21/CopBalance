package com.copito.copbalance.security.infrastructure.persitence.repository.token;

import com.copito.copbalance.security.domain.model.entity.Token;
import com.copito.copbalance.security.infrastructure.persitence.repository.Mapper;
import com.copito.copbalance.security.infrastructure.persitence.entity.TokenEntity;
import com.copito.copbalance.security.infrastructure.persitence.repository.account.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenMapper implements Mapper<Token, TokenEntity> {
    private final AccountMapper accountMapper;

    public TokenEntity toEntity(Token token){
        return new TokenEntity(
                token.getId(),
                token.getRandom(),
                accountMapper.toEntity(token.getAccount()),
                token.getCreatedAt(),
                token.getExpiresAt(),
                token.isExpired(),
                token.getType()

        );
    }

    public Token toDomain(TokenEntity entity){
        return new Token(
                entity.getId(),
                entity.getRandom(),
                accountMapper.toDomain(entity.getEntity()),
                entity.getCreatedAt(),
                entity.getExpiresAt(),
                entity.isExpired(),
                entity.getType()
        );
    }
}