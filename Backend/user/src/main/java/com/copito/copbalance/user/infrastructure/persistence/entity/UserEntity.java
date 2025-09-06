package com.copito.copbalance.user.infrastructure.persistence.entity;

import com.copito.copbalance.user.domain.model.enums.IdentificationTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserEntity {
    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private IdentificationTypeEnum identificationType;

    private Long identification;
    private String name;
    private String address;
}
