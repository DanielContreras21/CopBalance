package com.copito.copbalance.security.application.usecase;

import com.copito.copbalance.security.application.dto.response.AccountEmail;
import com.copito.copbalance.security.domain.model.entity.Account;
import com.copito.copbalance.security.domain.repository.AccountRepositoryPort;
import com.copito.copbalance.security.domain.usecase.GetSecurityEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetSecurityEmailServiceImp implements GetSecurityEmailService {

    private final AccountRepositoryPort repository;

    @Override
    public AccountEmail getEmail(String id) {
        Optional<Account> account = repository.findById(id);
        boolean isAccountExist = account.isPresent();

        if (!isAccountExist){
            throw new NoSuchElementException("La cuenta no existe");
        }

        AccountEmail email = new AccountEmail();
        email.setEmail(account.get().getEmail());

        return email;
    }
}
