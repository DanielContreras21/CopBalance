package com.copito.copbalance.security.application;

import com.copito.copbalance.security.domain.model.entity.Account;
import com.copito.copbalance.security.domain.model.entity.Token;
import com.copito.copbalance.security.domain.model.enums.TypeEnum;
import com.copito.copbalance.security.domain.repository.AccountRepositoryPort;
import com.copito.copbalance.security.domain.repository.TokenRepositoryPort;
import com.copito.copbalance.security.domain.usecase.ActivateAccountUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ActivateAccountUseCaseImp implements ActivateAccountUseCase {

    private final TokenRepositoryPort tokenRepository;
    private final AccountRepositoryPort accountRepository;

    @Override
    public void activate(String random) {
        Token token = tokenRepository.findByRandom(random)
                .orElseThrow(() -> new NoSuchElementException("Token no encontrado"));

        if (token.getExpiresAt().isBefore(LocalDateTime.now())) {
            token.setExpired(true);
        }

        if (token.getExpired){
            throw new IllegalStateException("El token ha expirado");
        }

        if (token.getTokenType() != TypeEnum.ACTIVATION) {
            throw new IllegalStateException("Token inválido para activación");
        }

        Account account = token.getAccount();
        account.setEnabled(true);
        accountRepository.save(account);
    }
}
