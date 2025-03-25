package com.copito.copbalance.security.infrastructure.persitence.repository.role;

import com.copito.copbalance.security.domain.model.entity.Role;
import com.copito.copbalance.security.domain.repository.RoleRepositoryPort;
import com.copito.copbalance.security.infrastructure.persitence.entity.RoleEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoleRepositoryAdapter implements RoleRepositoryPort {

    private final RoleJpaRepository roleJpaRepository;

    public RoleRepositoryAdapter(RoleJpaRepository roleJpaRepository){
        this.roleJpaRepository = roleJpaRepository;
    }

    @Override
    public Role save(Role role) {
        RoleEntity entity = toEntity(role);
        return toDomain(roleJpaRepository.save(entity));
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleJpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        roleJpaRepository.deleteById(id);
    }

    public Role toDomain(RoleEntity entity){
        return new Role(
                entity.getId(),
                entity.getName(),
                entity.getPermission()
        );
    }

    public RoleEntity toEntity(Role role){
        return new RoleEntity(
                role.getId(),
                role.getName(),
                role.getPermission()
        );
    }
}
