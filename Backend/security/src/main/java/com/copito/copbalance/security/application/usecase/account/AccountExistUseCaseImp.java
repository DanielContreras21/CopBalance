package com.copito.copbalance.security.application.usecase.account;

import com.copito.copbalance.security.application.dto.request.AccountExistRequest;
import com.copito.copbalance.security.domain.repository.AccountRepositoryPort;
import com.copito.copbalance.security.domain.usecase.account.AccountExistUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountExistUseCaseImp implements AccountExistUseCase {

    private final AccountRepositoryPort repository;

    @Override
    public void exist(AccountExistRequest request) {
        boolean isEmailExist = repository.findByEmail(request.getEmail()).isPresent();
        boolean isPhoneNumberExist = repository.findByPhoneNumber(request.getPhoneNumber()).isPresent();
        if (isEmailExist) {
            throw new DuplicateKeyException("El correo electrónico no se encuentra disponible");
        }
        if (isPhoneNumberExist) {
            throw new DuplicateKeyException("El teléfono no se encuentra disponible");
        }
    }
}
