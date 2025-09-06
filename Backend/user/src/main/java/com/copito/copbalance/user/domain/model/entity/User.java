package com.copito.copbalance.user.domain.model.entity;

import com.copito.copbalance.user.domain.model.enums.IdentificationTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
    private String id;
    private IdentificationTypeEnum identificationType;
    private Long identification;
    private String name;
    private String address;
}