package com.copito.copbalance.security.mapper;

import com.copito.copbalance.security.domain.model.dto.request.RegisterRequest;
import com.copito.copbalance.security.domain.model.dto.response.RegisterResponse;
import com.copito.copbalance.security.domain.model.entity.Account;
import com.copito.copbalance.security.domain.model.enums.RoleEnum;
import com.copito.copbalance.security.domain.model.mapper.account.RegisterMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class RegisterMapperTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private RegisterMapper mapper;

    static Stream<Arguments> providerRegisterRequests(){
        return Stream.of(
                Arguments.of(
                        new RegisterRequest("test@example.com", "test@example.com", "pass123", "pass123", "123456789"),
                        "pass123"
                ),
                Arguments.of(
                        new RegisterRequest("a@a.com", "a@a.com", "123", "123", "999999999"),
                        "123"
                ),
                Arguments.of(
                        new RegisterRequest("", "", "", "", ""),
                        ""
                ),
                Arguments.of(
                        new RegisterRequest(null, null, null, null, null),
                        null
                )
        );
    }

    @ParameterizedTest
    @MethodSource("providerRegisterRequests")
    void testToEntity(RegisterRequest request, String rawPassword){
        String encoded = passwordEncoder.encode(rawPassword);
        when(passwordEncoder.encode(rawPassword)).thenReturn(encoded);

        Account result = mapper.toEntity(request);

        assertEquals(request.getEmail(), result.getEmail());
        assertEquals(encoded, result.getPassword());
        assertEquals(request.getPhoneNumber(), result.getPhoneNumber());
    }


    public static Stream<Arguments> providerAccount(){
        LocalDate now = LocalDate.now();
        return Stream.of(
                Arguments.of(
                        Account.builder()
                                .id("1")
                                .email("test@example.com")
                                .password("encodedPassword")
                                .phoneNumber("123456789")
                                .role(RoleEnum.USER)
                                .createdAt(now)
                                .lastSession(now)
                                .AccountNonExpired(true)
                                .AccountNonLocked(true)
                                .CredentialsNonExpired(true)
                                .Enabled(true)
                                .build(),
                        RegisterResponse.builder()
                                .id("1")
                                .email("test@example.com")
                                .password("encodedPassword")
                                .phoneNumber("123456789")
                                .role(RoleEnum.USER)
                                .createdAt(now)
                                .lastSession(now)
                                .AccountNonExpired(true)
                                .AccountNonLocked(true)
                                .CredentialsNonExpired(true)
                                .Enabled(true)
                                .build()
                ),
                Arguments.of(
                        Account.builder().build(),
                        RegisterResponse.builder()
                                .id(null)
                                .email(null)
                                .password(null)
                                .phoneNumber(null)
                                .role(null)
                                .createdAt(null)
                                .lastSession(null)
                                .AccountNonExpired(false)
                                .AccountNonLocked(false)
                                .CredentialsNonExpired(false)
                                .Enabled(false)
                                .build()
                ),
                Arguments.of(
                        Account.builder()
                                .id("2")
                                .email("another@example.com")
                                .phoneNumber("987654321")
                                .role(RoleEnum.ADMIN)
                                .build(),
                        RegisterResponse.builder()
                                .id("2")
                                .email("another@example.com")
                                .password(null)
                                .phoneNumber("987654321")
                                .role(RoleEnum.ADMIN)
                                .createdAt(null)
                                .lastSession(null)
                                .AccountNonExpired(false)
                                .AccountNonLocked(false)
                                .CredentialsNonExpired(false)
                                .Enabled(false)
                                .build()
                )
        );
    }

    @ParameterizedTest
    @MethodSource("providerAccount")
    void testToDto(Account account, RegisterResponse expected){
        RegisterResponse response = mapper.toDto(account);

        assertEquals(expected.getId(), response.getId());
        assertEquals(expected.getEmail(), response.getEmail());
        assertEquals(expected.getPassword(), response.getPassword());
        assertEquals(expected.getPhoneNumber(), response.getPhoneNumber());
        assertEquals(expected.getRole(), response.getRole());
        assertEquals(expected.getCreatedAt(), response.getCreatedAt());
        assertEquals(expected.getLastSession(), response.getLastSession());
        assertEquals(expected.isAccountNonExpired(), response.isAccountNonExpired());
        assertEquals(expected.isAccountNonLocked(), response.isAccountNonLocked());
        assertEquals(expected.isCredentialsNonExpired(), response.isCredentialsNonExpired());
        assertEquals(expected.isEnabled(), response.isEnabled());
    }
}
