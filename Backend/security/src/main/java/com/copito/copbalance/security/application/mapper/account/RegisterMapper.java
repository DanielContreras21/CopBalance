package com.copito.copbalance.security.application.mapper.account;

import com.copito.copbalance.security.application.dto.request.RegisterRequest;
import com.copito.copbalance.security.application.dto.response.RegisterResponse;
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
                .name(request.getName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .build();
    }
    public RegisterResponse toDto(Account entity){
        return RegisterResponse.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .name(entity.getName())
                .lastName(entity.getLastName())
                .phoneNumber(entity.getPhoneNumber())
                .role(entity.getRole())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .lastSession(entity.getLastSession())
                .accountNonExpired(entity.isAccountNonExpired())
                .accountNonLocked(entity.isAccountNonLocked())
                .credentialsNonExpired(entity.isCredentialsNonExpired())
                .enabled(entity.isEnabled())
                .build();
    }
}
