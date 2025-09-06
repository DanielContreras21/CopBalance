package com.copito.copbalance.user.infrastructure.kafka.provider;

import com.copito.copbalance.user.application.events.EventPublisher;
import com.copito.copbalance.user.domain.events.UserCompletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCompletedPublisher implements EventPublisher<UserCompletedEvent> {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final static String TOPIC = "user-completed";

    @Override
    public void publish(UserCompletedEvent event) {
        kafkaTemplate.send(TOPIC, event);
    }
}
