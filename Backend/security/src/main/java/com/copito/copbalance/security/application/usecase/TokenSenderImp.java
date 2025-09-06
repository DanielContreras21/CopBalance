package com.copito.copbalance.security.application.usecase;

import com.copito.copbalance.security.domain.model.entity.Account;
import com.copito.copbalance.security.domain.model.entity.Token;
import com.copito.copbalance.security.domain.model.enums.TypeEnum;
import com.copito.copbalance.security.domain.repository.AccountRepositoryPort;
import com.copito.copbalance.security.domain.repository.TokenRepositoryPort;
import com.copito.copbalance.security.domain.usecase.TokenSender;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenSenderImp implements TokenSender {

    private final AccountRepositoryPort accountRepository;
    private final TokenRepositoryPort tokenRepository;

    @Override
    public void send(String id, TypeEnum type) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("El usuario no existe"));
        String random;
        Optional<Token> token;
        do {
            random = UUID.randomUUID().toString();
            token = tokenRepository.findByRandom(random);
        } while (token.isPresent());


        Token newToken = new Token();
        newToken.setRandom(random);
        newToken.setAccount(account);
        newToken.setCreatedAt(LocalDateTime.now());
        newToken.setExpiresAt(LocalDateTime.now().plusMinutes(30L));
        newToken.setType(type);

        tokenRepository.save(newToken);
    }
}

