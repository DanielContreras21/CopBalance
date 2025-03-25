package com.copito.copbalance.security.infrastructure.persitence.repository.account;

import com.copito.copbalance.security.infrastructure.persitence.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountJpaRepository extends JpaRepository<AccountEntity, String> {
    Optional<AccountEntity> findByEmail(String email);
}
