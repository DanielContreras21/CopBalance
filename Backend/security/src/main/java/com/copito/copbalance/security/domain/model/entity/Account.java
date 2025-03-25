package com.copito.copbalance.security.domain.model.entity;

import com.copito.copbalance.security.infrastructure.persitence.entity.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Account {
    private String id;
    private String email;
    private String password;
    private String phoneNumber;
    private Role role;
    private LocalDate createdAt;
    private LocalDate lastSession;
    private boolean AccountNonExpired;
    private boolean AccountNonLocked;
    private boolean CredentialsNonExpired;
    private boolean Enabled;
}
