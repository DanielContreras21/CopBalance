package com.copito.copbalance.security.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountExistRequest {
    String email;
    String phoneNumber;
}
