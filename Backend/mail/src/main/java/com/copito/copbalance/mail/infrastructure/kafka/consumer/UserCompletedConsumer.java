package com.copito.copbalance.mail.infrastructure.kafka.consumer;

import com.copito.copbalance.mail.domain.event.UserCompletedEvent;
import com.copito.copbalance.mail.domain.usecase.SendRegisterEmail;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class UserCompletedConsumer {

    private final SendRegisterEmail useCase;

    @KafkaListener(
            topics = "user-completed",
            groupId = "users-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(Map<String, Object> payload) {
        ObjectMapper mapper = new ObjectMapper();
        UserCompletedEvent event = mapper.convertValue(payload, UserCompletedEvent.class);
        System.out.println("Mail recibió UserCompletedEvent: " + event);
        useCase.send(event);
    }
}

