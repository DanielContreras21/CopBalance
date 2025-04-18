package com.copito.copbalance.security.domain.model.entity;

import com.copito.copbalance.security.domain.model.enums.TypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token {
    private Long id;
    private String random;
    private Account account;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private boolean expired;
    private TypeEnum type;
}
