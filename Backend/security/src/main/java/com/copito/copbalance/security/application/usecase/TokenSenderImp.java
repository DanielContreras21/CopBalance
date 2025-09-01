package com.copito.copbalance.security.application.usecase;

import com.copito.copbalance.security.domain.model.entity.Account;
import com.copito.copbalance.security.domain.model.entity.Token;
import com.copito.copbalance.security.domain.model.enums.TypeEnum;
import com.copito.copbalance.security.domain.repository.AccountRepositoryPort;
import com.copito.copbalance.security.domain.repository.TokenRepositoryPort;
import com.copito.copbalance.security.domain.usecase.EmailSender;
import com.copito.copbalance.security.domain.usecase.TokenSender;
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
public class TokenSenderImp implements TokenSender {

    private final AccountRepositoryPort accountRepository;
    private final TokenRepositoryPort tokenRepository;
    private final EmailSender emailSender;

    @Value("${base.url}")
    private String baseUrl;

    @Override
    public void send(String id, TypeEnum type, String subject, String template) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("El usuario no existe"));
        String email = account.getEmail();
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

        String link;

        if (type.equals(TypeEnum.ACTIVATION)){
            link = baseUrl + "auth/activate?random=" + newToken.getRandom();
        } else {
            link = baseUrl + "auth/recoverPassword?random=" + newToken.getRandom();
        }

        Map<String, Object> variables = Map.of(
                "name", account.getName(),
                "lastName", account.getLastName(),
                "link", link
        );

        try{
            emailSender.sendMail(email, subject, template, variables);
        } catch (MessagingException e){
            throw new  IllegalStateException("No se pudo enviar el correo", e);
        }

    }
}

