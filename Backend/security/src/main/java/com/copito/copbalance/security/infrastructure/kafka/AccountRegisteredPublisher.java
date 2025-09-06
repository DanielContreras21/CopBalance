package com.copito.copbalance.security.infrastructure.kafka;

import com.copito.copbalance.security.application.events.EventPublisher;
import com.copito.copbalance.security.domain.events.AccountRegisteredEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountRegisteredPublisher implements EventPublisher<AccountRegisteredEvent> {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final static String TOPIC = "account-registered";

    @Override
    public void publish(AccountRegisteredEvent event) {
        kafkaTemplate.send(TOPIC, event.getId());
    }
}
