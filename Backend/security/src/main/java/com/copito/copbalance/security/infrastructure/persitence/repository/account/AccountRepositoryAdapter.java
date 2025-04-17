package com.copito.copbalance.security.infrastructure.persitence.repository.account;

import com.copito.copbalance.security.domain.model.entity.Account;
import com.copito.copbalance.security.domain.repository.AccountRepositoryPort;
import com.copito.copbalance.security.infrastructure.persitence.entity.AccountEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryAdapter implements AccountRepositoryPort {
    private final AccountJpaRepository accountJpaRepository;

    @Override
    public Account save(Account account) {
        AccountEntity entity = toEntity(account);
        return toDomain(accountJpaRepository.save(entity));
    }

    @Override
    public Optional<Account> findByEmail(String email) {
        if (email == null){
            throw new IllegalArgumentException("El email no puede ser null");
        }
        return accountJpaRepository.findByEmail(email).map(this::toDomain);
    }

    @Override
    public Optional<Account> findById(String id) {
        if (id == null){
            throw new IllegalArgumentException("El id no puede ser null");
        }
        return accountJpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public void deleteById(String id) {
        accountJpaRepository.deleteById(id);
    }

    public AccountEntity toEntity(Account account){
        return new AccountEntity(
                account.getId(),
                account.getEmail(),
                account.getPassword(),
                account.getPhoneNumber(),
                account.getRole(),
                account.getCreatedAt(),
                account.getUpdatedAt(),
                account.getLastSession(),
                account.isAccountNonExpired(),
                account.isAccountNonLocked(),
                account.isCredentialsNonExpired(),
                account.isEnabled()
        );
    }

    public Account toDomain(AccountEntity entity){
        return new Account(
                entity.getId(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getPhoneNumber(),
                entity.getRole(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getLastSession(),
                entity.isAccountNonExpired(),
                entity.isAccountNonLocked(),
                entity.isCredentialsNonExpired(),
                entity.isEnabled()
        );
    }
}
