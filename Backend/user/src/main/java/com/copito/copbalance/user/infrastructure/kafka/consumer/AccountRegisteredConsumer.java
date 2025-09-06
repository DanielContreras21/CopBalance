package com.copito.copbalance.user.infrastructure.kafka.consumer;

import com.copito.copbalance.user.domain.events.AccountRegisteredEvent;
import com.copito.copbalance.user.domain.usecase.CreateUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountRegisteredConsumer {

    private final CreateUserUseCase useCase;

    @KafkaListener(
            topics = "account-registered",
            groupId = "users-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(AccountRegisteredEvent event) {
        System.out.println("📩 Users recibió AccountRegisteredEvent: " + event);
        useCase.createUSer(event.getId());
    }
}

