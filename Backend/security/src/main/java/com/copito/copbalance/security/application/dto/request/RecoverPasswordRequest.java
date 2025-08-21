package com.copito.copbalance.security.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecoverPasswordRequest {
    private String password;
    private String confirmPassword;
}
