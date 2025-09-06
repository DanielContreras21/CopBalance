package com.copito.copbalance.user.domain.repository;

import com.copito.copbalance.user.domain.model.entity.User;

import java.util.Optional;

public interface UserRepositoryPort {
    User save(User account);
    Optional<User> findById(String id);
}
