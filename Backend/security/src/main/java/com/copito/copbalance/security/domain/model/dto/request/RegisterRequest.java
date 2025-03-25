package com.copito.copbalance.security.domain.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String email;
    private String confirmEmail;
    private String password;
    private String confirmPassword;
    private String phoneNumber;
}
