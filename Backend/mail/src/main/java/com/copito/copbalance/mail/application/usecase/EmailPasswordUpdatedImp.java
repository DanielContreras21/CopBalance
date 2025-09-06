//package com.copito.copbalance.mail.application.usecase;
//
//import com.copito.copbalance.mail.domain.usecase.EmailPasswordUpdated;
//import com.copito.copbalance.mail.domain.usecase.EmailSender;
//import jakarta.mail.MessagingException;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.Map;
//
//@Service
//@RequiredArgsConstructor
//public class EmailPasswordUpdatedImp implements EmailPasswordUpdated {
//
//    private final EmailSender emailSender;
//
//    @Override
//    public void send(Account account) {
//        Map<String, Object> variables = Map.of(
//                "name", account.getName(),
//                "lastName", account.getLastName()
//        );
//
//        try{
//            emailSender.sendMail(account.getEmail(), "Cambio de contraseña Exitoso", "password_reset_confirm.html", variables);
//        } catch (MessagingException e){
//            throw new  IllegalStateException("No se pudo enviar el correo", e);
//        }
//    }
//}
