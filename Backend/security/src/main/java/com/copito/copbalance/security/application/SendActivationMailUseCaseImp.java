package com.copito.copbalance.security.application;

import com.copito.copbalance.security.domain.model.entity.Account;
import com.copito.copbalance.security.domain.model.entity.Token;
import com.copito.copbalance.security.domain.model.enums.TypeEnum;
import com.copito.copbalance.security.domain.repository.AccountRepositoryPort;
import com.copito.copbalance.security.domain.repository.TokenRepositoryPort;
import com.copito.copbalance.security.domain.usecase.EmailSender;
import com.copito.copbalance.security.domain.usecase.SendActivationMailUseCase;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SendActivationMailUseCaseImp implements SendActivationMailUseCase {
    private final EmailSender emailSender;
    private final AccountRepositoryPort accountRepository;
    private final TokenRepositoryPort tokenRepository;

    @Value("${base.url}")
    private String baseUrl;

    @Override
    public void send(String id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("El usuario no existe"));
        String email = account.getEmail();
        String random;
        Optional<Token> token;
        do {
            random = UUID.randomUUID().toString();
            token = tokenRepository.findByRandom(random);
        } while (token.isPresent());

        Token newToken = Token.builder()
                .random(random)
                .account(account)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(30L))
                .tokenType(TypeEnum.ACTIVATION)
                .build();

        tokenRepository.save(newToken);

        String activationLink = baseUrl + "auth/activate?token=" + newToken.getRandom();

        Map<String, Object> variables = Map.of(
                "name", account.getEmail(),
                "activationLink", activationLink //Cambiar a nombre cuando se cree el User
        );

        try{
            emailSender.sendMail(email, "Activa tu cuenta en CopBalance", "activation.html", variables);
        } catch (MessagingException e){
            throw new  IllegalStateException("No se pudo enviar el correo de activación", e);
        }

    }
}
