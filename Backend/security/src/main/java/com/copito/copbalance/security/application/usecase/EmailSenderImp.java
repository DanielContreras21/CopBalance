package com.copito.copbalance.security.application.usecase;

import com.copito.copbalance.security.domain.usecase.EmailSender;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailSenderImp implements EmailSender {
    private final TemplateEngine templateEngine;
    private final JavaMailSender mailSender;

    public void sendMail(String to, String subject, String template, Map<String, Object> variables) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(to);
        helper.setSubject(subject);

        Context context = new Context();

        context.setVariables(variables);
        String htmlContent = templateEngine.process(template, context);

        helper.setText(htmlContent, true);

        mailSender.send(message);
    }
}
