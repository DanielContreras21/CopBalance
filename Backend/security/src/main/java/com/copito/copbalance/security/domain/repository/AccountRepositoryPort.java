package com.copito.copbalance.security.domain.repository;

import com.copito.copbalance.security.domain.model.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

public interface AccountRepositoryPort {
    Account save(Account account);
    Optional<Account> findByEmail(String email);
    Optional<Account> findById(String id);
    Optional<Account> findByPhoneNumber(String phoneNumber);
    void deleteById(String id);
}
