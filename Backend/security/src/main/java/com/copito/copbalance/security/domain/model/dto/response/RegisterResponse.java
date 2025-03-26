package com.copito.copbalance.security.domain.model.dto.response;

import com.copito.copbalance.security.domain.model.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponse {
    private String id;
    private String email;
    private String password;
    private String phoneNumber;
    private RoleEnum role;
    private LocalDate createdAt;
    private LocalDate lastSession;
    private boolean AccountNonExpired;
    private boolean AccountNonLocked;
    private boolean CredentialsNonExpired;
    private boolean Enabled;
}
