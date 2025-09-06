package com.copito.copbalance.user.application.usecase;

import com.copito.copbalance.user.application.events.EventPublisher;
import com.copito.copbalance.user.domain.events.AccountRegisteredEvent;
import com.copito.copbalance.user.domain.events.UserCompletedEvent;
import com.copito.copbalance.user.domain.model.entity.User;
import com.copito.copbalance.user.domain.repository.UserRepositoryPort;
import com.copito.copbalance.user.domain.usecase.CompleteUserProfile;
import com.copito.copbalance.user.infrastructure.client.AccountClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompleteUserProfileImp implements CompleteUserProfile {

    private final UserRepositoryPort repository;
    private final EventPublisher<UserCompletedEvent> eventPublisher;
    private final AccountClient client;

    @Override
    public User complete(User request) {
        Optional<User> user = repository.findById(request.getId());
        user.orElseThrow(() -> new NoSuchElementException("El usuario no existe"));
        User userEntity = user.get();


        userEntity.setId(userEntity.getId());
        userEntity.setIdentificationType(request.getIdentificationType());
        userEntity.setIdentification(request.getIdentification());
        userEntity.setName(request.getName());
        userEntity.setAddress(request.getAddress());

        String email = client.getAccountEmail(request.getId()).getEmail();

        UserCompletedEvent event = new UserCompletedEvent(userEntity.getName(), email);
        eventPublisher.publish(event);

        return repository.save(userEntity);
    }
}
