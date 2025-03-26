package com.copito.copbalance.security.domain.model.mapper.account;

import com.copito.copbalance.security.domain.model.dto.request.RegisterRequest;
import com.copito.copbalance.security.domain.model.dto.response.RegisterResponse;
import com.copito.copbalance.security.domain.model.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RegisterMapper {

    @Autowired
    private PasswordEncoder encoder;

    public Account toEntity(RegisterRequest request){
        return Account.builder()
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .build();
    }
    public RegisterResponse toDto(Account entity){
        return RegisterResponse.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .phoneNumber(entity.getPhoneNumber())
                .role(entity.getRole())
                .createdAt(entity.getCreatedAt())
                .lastSession(entity.getLastSession())
                .AccountNonExpired(entity.isAccountNonExpired())
                .AccountNonLocked(entity.isAccountNonLocked())
                .CredentialsNonExpired(entity.isCredentialsNonExpired())
                .Enabled(entity.isEnabled())
                .build();
    }
}
