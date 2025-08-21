package com.copito.copbalance.security.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountRequest {
    private String email;
    private String name;
    private String lastName;
    private String phoneNumber;
}
