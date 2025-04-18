package com.copito.copbalance.security.domain.model.dto.request;

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
