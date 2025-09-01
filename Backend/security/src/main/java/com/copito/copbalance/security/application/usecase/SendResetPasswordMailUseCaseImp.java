package com.copito.copbalance.security.application.usecase;

import com.copito.copbalance.security.domain.model.enums.TypeEnum;
import com.copito.copbalance.security.domain.usecase.SendResetPasswordMailUseCase;
import com.copito.copbalance.security.domain.usecase.TokenSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendResetPasswordMailUseCaseImp implements SendResetPasswordMailUseCase {

    private final TokenSender tokenSender;

    @Override
    public void send(String id) {
        tokenSender.send(id, TypeEnum.PASSWORD_RESET, "Recupera tu contraseña de CopBalance", "password_recovery.html");
    }
}
