package com.copito.copbalance.security.domain.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequest {
    private String email;
    private String confirmEmail;
    private String password;
    private String confirmPassword;
    private String name;
    private String lastName;
    private String phoneNumber;
}
