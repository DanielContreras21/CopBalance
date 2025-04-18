package com.copito.copbalance.security.infrastructure.persitence.entity;

import com.copito.copbalance.security.domain.model.enums.TypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tokens")
public class TokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String random;

    @ManyToOne
    private AccountEntity entity;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private boolean expired;
    private TypeEnum type;
}
