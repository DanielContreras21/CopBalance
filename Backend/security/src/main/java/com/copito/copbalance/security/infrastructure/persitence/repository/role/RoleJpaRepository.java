package com.copito.copbalance.security.infrastructure.persitence.repository.role;

import com.copito.copbalance.security.infrastructure.persitence.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleJpaRepository extends JpaRepository<RoleEntity, Long> {
}
