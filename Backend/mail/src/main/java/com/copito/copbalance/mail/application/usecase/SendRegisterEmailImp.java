package com.copito.copbalance.mail.application.usecase;

import com.copito.copbalance.mail.application.dto.request.EmailRequest;
import com.copito.copbalance.mail.domain.event.UserCompletedEvent;
import com.copito.copbalance.mail.domain.usecase.EmailSender;
import com.copito.copbalance.mail.domain.usecase.SendRegisterEmail;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class SendRegisterEmailImp implements SendRegisterEmail {

    private final EmailSender emailSender;

    @Override
    public void send(UserCompletedEvent event) {

        Map<String, Object> variables = Map.of(
                "name", event.getName()
        );

        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setTo(event.getEmail());
        emailRequest.setTemplate("register.html");
        emailRequest.setVariables(variables);
        emailRequest.setSubject("Registraste tu cuenta en CopBalance");

        try{
            emailSender.sendMail(emailRequest);
        } catch (MessagingException e){
            throw new  IllegalStateException("No se pudo enviar el correo de activación", e);
        }
    }
}
