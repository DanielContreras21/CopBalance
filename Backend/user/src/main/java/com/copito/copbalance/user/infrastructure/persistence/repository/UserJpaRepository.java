package com.copito.copbalance.user.infrastructure.persistence.repository;

import com.copito.copbalance.user.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, String> {
}
