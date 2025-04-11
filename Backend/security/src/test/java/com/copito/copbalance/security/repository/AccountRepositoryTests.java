package com.copito.copbalance.security.repository;

import com.copito.copbalance.security.domain.model.entity.Account;
import com.copito.copbalance.security.domain.model.enums.RoleEnum;
import com.copito.copbalance.security.infrastructure.persitence.entity.AccountEntity;
import com.copito.copbalance.security.infrastructure.persitence.repository.account.AccountJpaRepository;
import com.copito.copbalance.security.infrastructure.persitence.repository.account.AccountRepositoryAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

@ExtendWith({org.mockito.junit.jupiter.MockitoExtension.class})
public class AccountRepositoryTests {

    @Mock
    private AccountJpaRepository accountJpaRepository;

    private AccountRepositoryAdapter accountRepositoryAdapter;

    @BeforeEach
    void seUp(){
        accountRepositoryAdapter = new AccountRepositoryAdapter(accountJpaRepository);
    }

    public static Stream<Arguments> findByIdCases() {
        LocalDate now = LocalDate.now();
        AccountEntity validEntity = new AccountEntity(
                "123", "test@example.com", "pass", "3000000000",
                RoleEnum.USER, now, now, true, true, true, true
        );
        Account expectedAccount = new Account(
                "123", "test@example.com", "pass", "3000000000",
                RoleEnum.USER, now, now, true, true, true, true
        );

        return Stream.of(
                Arguments.of("123", Optional.of(validEntity), Optional.of(expectedAccount)),
                Arguments.of("999", Optional.empty(), Optional.empty()),
                Arguments.of(null, null, null),
                Arguments.of("", Optional.empty(), Optional.empty()),
                Arguments.of("@!#", Optional.empty(), Optional.empty())
        );
    }

    @ParameterizedTest
    @MethodSource("findByIdCases")
    void testFindById(String id, Optional<AccountEntity> dbResult, Optional<Account> expected) {
        if (id != null) {
            when(accountJpaRepository.findById(id)).thenReturn(dbResult);
            Optional<Account> result = accountRepositoryAdapter.findById(id);
            assertEquals(expected, result);
        } else {
            assertThrows(IllegalArgumentException.class, () -> accountRepositoryAdapter.findById(null));
        }
    }

    public static Stream<Arguments> findByEmailCases() {
        LocalDate now = LocalDate.now();
        AccountEntity validEntity = new AccountEntity(
                "123", "test@example.com", "pass", "3000000000",
                RoleEnum.USER, now, now, true, true, true, true
        );
        Account expectedAccount = new Account(
                "123", "test@example.com", "pass", "3000000000",
                RoleEnum.USER, now, now, true, true, true, true
        );

        return Stream.of(
                Arguments.of("123", Optional.of(validEntity), Optional.of(expectedAccount)),
                Arguments.of("999", Optional.empty(), Optional.empty()),
                Arguments.of(null, null, null),
                Arguments.of("", Optional.empty(), Optional.empty()),
                Arguments.of("@!#", Optional.empty(), Optional.empty())
        );
    }

    @ParameterizedTest
    @MethodSource("findByEmailCases")
    void testFindByEmail(String email, Optional<AccountEntity> dbResult, Optional<Account> expected){
        if (email != null){
            when(accountJpaRepository.findByEmail(email)).thenReturn(dbResult);
            Optional<Account> result = accountRepositoryAdapter.findByEmail(email);
            assertEquals(expected, result);
        } else {
            assertThrows(IllegalArgumentException.class, () -> accountRepositoryAdapter.findByEmail(null));
        }
    }

    private static Stream<Arguments> provideAccountsForSave() {
        LocalDate now = LocalDate.now();

        Account account1 = new Account("123", "test1@example.com", "pass", "3000000000",
                RoleEnum.USER, now, now, true, true, true, true);
        AccountEntity entity1 = new AccountEntity("123", "test1@example.com", "pass", "3000000000",
                RoleEnum.USER, now, now, true, true, true, true);

        Account account2 = new Account("456", "test2@example.com", "1234", "3111111111",
                RoleEnum.ADMIN, now, now, false, true, false, true);
        AccountEntity entity2 = new AccountEntity("456", "test2@example.com", "1234", "3111111111",
                RoleEnum.ADMIN, now, now, false, true, false, true);

        return Stream.of(
                Arguments.of(account1, entity1, account1),
                Arguments.of(account2, entity2, account2)
        );
    }

    @ParameterizedTest
    @MethodSource("provideAccountsForSave")
    void testSaveAccount(Account inputAccount, AccountEntity savedEntity, Account expectedAccount) {
        when(accountJpaRepository.save(any(AccountEntity.class))).thenReturn(savedEntity);
        Account result = accountRepositoryAdapter.save(inputAccount);
        assertEquals(expectedAccount, result);
        verify(accountJpaRepository).save(any(AccountEntity.class));
    }

    private static Stream<Arguments> provideIdsForDelete() {
        return Stream.of(
                Arguments.of("123"),
                Arguments.of("456"),
                Arguments.of("test-id"),
                Arguments.of(""),
                Arguments.of("@#$%")
        );
    }

    @ParameterizedTest
    @MethodSource("provideIdsForDelete")
    void testDeleteById(String id) {
        accountRepositoryAdapter.deleteById(id);
        verify(accountJpaRepository).deleteById(id);
    }
}
