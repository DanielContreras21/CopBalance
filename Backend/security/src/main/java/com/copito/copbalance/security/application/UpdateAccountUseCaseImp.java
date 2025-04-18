package com.copito.copbalance.security.application;

import com.copito.copbalance.security.domain.model.dto.request.AccountRequest;
import com.copito.copbalance.security.domain.model.entity.Account;
import com.copito.copbalance.security.domain.repository.AccountRepositoryPort;
import com.copito.copbalance.security.domain.usecase.UpdateAccountUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateAccountUseCaseImp implements UpdateAccountUseCase {

    private final AccountRepositoryPort repository;

    @Override
    public void update(String id, AccountRequest request) {
        Account account = repository.findById(id).get();

        account.setEmail(request.getEmail() != null && !request.getEmail().isEmpty() ? request.getEmail() : account.getEmail());
        account.setName(request.getName() != null && !request.getName().isEmpty() ? request.getName() : account.getName());
        account.setLastName(request.getLastName() != null && !request.getLastName().isEmpty() ? request.getLastName() : account.getLastName());
        account.setPhoneNumber(request.getPhoneNumber() != null && !request.getPhoneNumber().isEmpty() ? request.getPhoneNumber() : account.getPhoneNumber());

        repository.save(account);
    }
}
