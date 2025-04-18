package com.copito.copbalance.security.domain.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatePasswordRequest {
    private String currentPassword;
    private String newPassword;
    private String confirmNewPassword;
}
