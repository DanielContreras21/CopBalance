package com.copito.copbalance.security.domain.model.entity;

import com.copito.copbalance.security.domain.model.enums.PermissionEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {
    private Long id;
    private String name;
    private Set<PermissionEnum> permission;
}
