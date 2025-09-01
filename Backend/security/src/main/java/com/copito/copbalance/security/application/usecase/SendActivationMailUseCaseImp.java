package com.copito.copbalance.security.application.usecase;

import com.copito.copbalance.security.domain.model.enums.TypeEnum;
import com.copito.copbalance.security.domain.usecase.SendActivationMailUseCase;
import com.copito.copbalance.security.domain.usecase.TokenSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendActivationMailUseCaseImp implements SendActivationMailUseCase {
    private final TokenSender tokenSender;

    @Override
    public void send(String id) {
        tokenSender.send(id, TypeEnum.ACTIVATION, "Activa tu cuenta en CopBalance", "activation.html");
    }
}
