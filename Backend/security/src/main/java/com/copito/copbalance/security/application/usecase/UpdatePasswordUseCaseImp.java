package com.copito.copbalance.security.application;

import com.copito.copbalance.security.application.dto.request.UpdatePasswordRequest;
import com.copito.copbalance.security.domain.model.entity.Account;
import com.copito.copbalance.security.domain.repository.AccountRepositoryPort;
import com.copito.copbalance.security.domain.usecase.UpdatePasswordUseCase;
import com.copito.copbalance.security.infrastructure.security.UserDetailsServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdatePasswordUseCaseImp implements UpdatePasswordUseCase {
    private final AccountRepositoryPort repository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsServiceImp userDetailsService;

    @Override
    public void updatePassword(String id, UpdatePasswordRequest request) {
        Account account = repository.findById(id).get();
        UserDetails userDetails = userDetailsService.loadUserByUsername(account.getEmail());

        if (!passwordEncoder.matches(request.getCurrentPassword(), userDetails.getPassword())){
            throw new IllegalArgumentException("Contraseña incorrecta");
        };
        if (!request.getNewPassword().equals(request.getConfirmNewPassword())){
            throw new IllegalArgumentException("las contraseñas no coinciden");
        }

        account.setPassword(passwordEncoder.encode(request.getNewPassword()));
        repository.save(account);
    }
}
