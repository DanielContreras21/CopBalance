package com.copito.copbalance.security.domain.repository;

import com.copito.copbalance.security.domain.model.entity.Role;

import java.util.Optional;

public interface RoleRepositoryPort {
    Role save(Role role);
    Optional<Role> findById(Long id);
    void deleteById(Long id);
}
