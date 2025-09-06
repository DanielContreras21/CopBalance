package com.copito.copbalance.security.infrastructure.persitence.repository.account;

import com.copito.copbalance.security.domain.model.entity.Account;
import com.copito.copbalance.security.infrastructure.persitence.repository.Mapper;
import com.copito.copbalance.security.infrastructure.persitence.entity.AccountEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper implements Mapper<Account, AccountEntity> {

    @Override
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

    @Override
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
