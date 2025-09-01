package com.copito.copbalance.security.application.usecase;

import com.copito.copbalance.security.application.dto.request.RecoverPasswordRequest;
import com.copito.copbalance.security.domain.model.entity.Account;
import com.copito.copbalance.security.domain.model.entity.Token;
import com.copito.copbalance.security.domain.model.enums.TypeEnum;
import com.copito.copbalance.security.domain.repository.AccountRepositoryPort;
import com.copito.copbalance.security.domain.repository.TokenRepositoryPort;
import com.copito.copbalance.security.domain.usecase.EmailSender;
import com.copito.copbalance.security.domain.usecase.RecoverPasswordUseCase;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RecoverPasswordUseCaseImp implements RecoverPasswordUseCase {

    private final EmailSender emailSender;
    private final AccountRepositoryPort repository;
    private final TokenRepositoryPort tokenRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void updatePassword(String random, RecoverPasswordRequest request) {
        Token token = tokenRepository.findByRandom(random). orElseThrow(() -> new NoSuchElementException("Token no encontrado"));

        if (token.getExpiresAt().isBefore(LocalDateTime.now())) {
            token.setExpired(true);
        }

        if (token.isExpired()){
            throw new IllegalStateException("El token ha expirado");
        }

        if (token.getType() != TypeEnum.PASSWORD_RESET) {
            throw new IllegalStateException("Token inválido para activación");
        }

        if (!request.getPassword().equals(request.getConfirmPassword())){
            throw new IllegalArgumentException("Las contraseñas no coinciden");
        }

        Account account = token.getAccount();
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        repository.save(account);


        Map<String, Object> variables = Map.of(
                "name", account.getName(),
                "lastName", account.getLastName()
        );

        try{
            emailSender.sendMail(account.getEmail(), "Cambio de contraseña Exitoso", "password_reset_confirm.html", variables);
        } catch (MessagingException e){
            throw new  IllegalStateException("No se pudo enviar el correo", e);
        }
    }
}
