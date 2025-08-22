package com.copito.copbalance.security.infrastructure.persitence.repository.account;

import com.copito.copbalance.security.domain.model.entity.Account;
import com.copito.copbalance.security.domain.repository.AccountRepositoryPort;
import com.copito.copbalance.security.infrastructure.persitence.entity.AccountEntity;
import com.copito.copbalance.security.infrastructure.persitence.repository.Mapper;
import com.netflix.discovery.converters.Auto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryAdapter implements AccountRepositoryPort {
    private final AccountJpaRepository accountJpaRepository;
    private final AccountMapper accountMapper;

    @Override
    public Account save(Account account) {
        AccountEntity entity = accountMapper.toEntity(account);
        return accountMapper.toDomain(accountJpaRepository.save(entity));
    }

    @Override
    public Optional<Account> findByEmail(String email) {
        if (email == null){
            throw new IllegalArgumentException("El email no puede ser null");
        }
        return accountJpaRepository.findByEmail(email).map(accountMapper::toDomain);
    }

    @Override
    public Optional<Account> findById(String id) {
        if (id == null){
            throw new IllegalArgumentException("El id no puede ser null");
        }
        return accountJpaRepository.findById(id).map(accountMapper::toDomain);
    }

    @Override
    public void deleteById(String id) {
        accountJpaRepository.deleteById(id);
    }
}
