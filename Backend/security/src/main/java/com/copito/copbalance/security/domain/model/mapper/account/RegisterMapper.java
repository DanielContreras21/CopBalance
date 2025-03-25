package com.copito.copbalance.security.domain.model.mapper.account;

import com.copito.copbalance.security.domain.model.dto.request.RegisterRequest;
import com.copito.copbalance.security.domain.model.dto.response.RegisterResponse;
import com.copito.copbalance.security.domain.model.entity.Account;
import com.copito.copbalance.security.domain.model.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@RequiredArgsConstructor
@Component
public class RegisterMapper {

    public Account toEntity(RegisterRequest request){
        return Account.builder()
                .email(request.getEmail())
                .password(request.getPassword())
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
