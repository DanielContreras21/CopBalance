package com.copito.copbalance.security.infrastructure.persitence.repository.token;

import com.copito.copbalance.security.infrastructure.persitence.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenJpaRepository extends JpaRepository<TokenEntity, Long> {
    Optional<TokenEntity> findByRandom(String random);
}
