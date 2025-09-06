//package com.copito.copbalance.mail.application.usecase;
//
//import com.copito.copbalance.mail.application.dto.request.EmailRequest;
//import com.copito.copbalance.mail.domain.usecase.EmailSender;
//import com.copito.copbalance.mail.domain.usecase.TokenSender;
//import jakarta.mail.MessagingException;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.util.Map;
//
//@Service
//@RequiredArgsConstructor
//public class TokenSenderImp implements TokenSender {
//
//    private final EmailSender emailSender;
//
//    @Value("${base.url}")
//    private String baseUrl;
//
//    @Override
//    public void send(Token token, Account account, String subject, String template) {
//
//        String link;
//
//            if (token.type.equals("ACTIVATION")){
//            link = baseUrl + "auth/activate?random=" + token.getRandom();
//        } else {
//            link = baseUrl + "auth/recoverPassword?random=" + token.getRandom();
//        }
//
//        Map<String, Object> variables = Map.of(
//                "name", account.getName(),
//                "lastName", account.getLastName(),
//                "link", link
//        );
//
//        EmailRequest emailRequest = new EmailRequest();
//
//        emailRequest.setTo(account.email);
//        emailRequest.setSubject(subject);
//        emailRequest.setTemplate(template);
//        emailRequest.setVariables(variables);
//
//            try{
//            emailSender.sendMail(emailRequest);
//        } catch (MessagingException e){
//            throw new  IllegalStateException("No se pudo enviar el correo", e);
//        }
//    }
//}
