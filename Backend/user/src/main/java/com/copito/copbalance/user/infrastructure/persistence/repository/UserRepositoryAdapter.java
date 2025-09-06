package com.copito.copbalance.user.infrastructure.persistence.repository;

import com.copito.copbalance.user.domain.model.entity.User;
import com.copito.copbalance.user.domain.repository.UserRepositoryPort;
import com.copito.copbalance.user.infrastructure.persistence.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {
    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    @Override
    public User save(User user) {
        UserEntity entity = userMapper.toEntity(user);
        return userMapper.toDomain(userJpaRepository.save(entity));
    }

    @Override
    public Optional<User> findById(String id) {
        if (id == null){
            throw new IllegalArgumentException("El id no puede ser null");
        }
        return userJpaRepository.findById(id).map(userMapper::toDomain);
    }
}

