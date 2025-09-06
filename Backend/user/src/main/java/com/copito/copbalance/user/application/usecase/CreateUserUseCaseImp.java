package com.copito.copbalance.user.application.usecase;

import com.copito.copbalance.user.domain.model.entity.User;
import com.copito.copbalance.user.domain.repository.UserRepositoryPort;
import com.copito.copbalance.user.domain.usecase.CreateUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CreateUserUseCaseImp implements CreateUserUseCase {

    private final UserRepositoryPort repository;

    @Override
    public User createUSer(String id) {
        boolean isUserExist = repository.findById(id).isPresent();

        if (isUserExist){
            throw new DuplicateKeyException("El usuario con el id " + id + " ya existe");
        }

        User user = new User();
        user.setId(id);
        return repository.save(user);
    }
}
