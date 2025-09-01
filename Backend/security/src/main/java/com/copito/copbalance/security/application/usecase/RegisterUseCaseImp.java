package com.copito.copbalance.security.application.usecase;

import com.copito.copbalance.security.application.dto.request.RegisterRequest;
import com.copito.copbalance.security.application.dto.response.RegisterResponse;
import com.copito.copbalance.security.domain.model.entity.Account;
import com.copito.copbalance.security.domain.model.enums.RoleEnum;
import com.copito.copbalance.security.application.mapper.account.RegisterMapper;
import com.copito.copbalance.security.domain.repository.AccountRepositoryPort;
import com.copito.copbalance.security.domain.usecase.EmailSender;
import com.copito.copbalance.security.domain.usecase.RegisterUseCase;
import com.copito.copbalance.security.utils.EmailValidator;
import com.copito.copbalance.security.utils.PhoneValidator;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RegisterUseCaseImp implements RegisterUseCase {
    private final AccountRepositoryPort repository;
    private final RegisterMapper mapper;
    private final EmailSender emailSender;

    @Override
    public RegisterResponse register(RegisterRequest request) {
        boolean isEmailExist = repository.findByEmail(request.getEmail()).isPresent();
        boolean isPhoneNumberExist = repository.findByPhoneNumber(request.getPhoneNumber()).isPresent();

        if (!EmailValidator.isEmailValid(request.getEmail())){
            throw new IllegalArgumentException("Ingrese un correo electrónico válido");
        }
        if (isEmailExist){
            throw new DuplicateKeyException("El correo electrónico no se encuentra disponible");
        }
        if (!Objects.equals(request.getEmail(), request.getConfirmEmail())){
            throw new IllegalArgumentException("Los correos electrónicos no coinciden");
        }
        if (!Objects.equals(request.getPassword(), request.getConfirmPassword())){
            throw new IllegalArgumentException("Las contraseñas no coinciden");
        }
        if (request.getPassword().length() <6){
            throw new IllegalArgumentException("La contraseña debe ser mayor o igual a 6 dígitos");
        }
        if (isPhoneNumberExist){
            throw new DuplicateKeyException("El teléfono no se encuentra disponible");
        }
        if (!PhoneValidator.isValidColombianPhone(request.getPhoneNumber())){
            throw new IllegalArgumentException("Ingrese un número de teléfono correcto");
        }

        Account account = mapper.toEntity(request);
        account.setRole(RoleEnum.USER);
        account.setCreatedAt(LocalDateTime.now());
        account.setAccountNonExpired(true);
        account.setAccountNonLocked(true);
        account.setCredentialsNonExpired(true);
        account.setEnabled(false);
        Account saveAccount = repository.save(account);

        Map<String, Object> variables = Map.of(
                "name", account.getName(),
                "lastName", account.getLastName()
        );

        try{
            emailSender.sendMail(account.getEmail(), "Registraste tu cuenta en CopBalance", "register.html", variables);
        } catch (MessagingException e){
            throw new  IllegalStateException("No se pudo enviar el correo de activación", e);
        }

        return mapper.toDto(saveAccount);
    }
}
