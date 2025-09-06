package com.copito.copbalance.mail.application.usecase;

import com.copito.copbalance.mail.application.dto.request.EmailRequest;
import com.copito.copbalance.mail.domain.usecase.EmailSender;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class EmailSenderImp implements EmailSender {
    private final TemplateEngine templateEngine;
    private final JavaMailSender mailSender;

    public void sendMail(EmailRequest emailRequest) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(emailRequest.getTo());
        helper.setSubject(emailRequest.getSubject());

        Context context = new Context();

        context.setVariables(emailRequest.getVariables());
        String htmlContent = templateEngine.process(emailRequest.getTemplate(), context);

        helper.setText(htmlContent, true);

        mailSender.send(message);
    }
}
