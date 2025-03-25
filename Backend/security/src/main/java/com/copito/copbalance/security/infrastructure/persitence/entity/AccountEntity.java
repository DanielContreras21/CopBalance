package com.copito.copbalance.security.infrastructure.persitence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Entity
@Table(name = "accounts")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountEntity {
    @Id
    private String id;

    @Email
    @Column(unique = true, name = "email", nullable = false)
    @NotBlank
    private String email;

    @Column(name = "password")
    @Length(min = 6)
    private String password;

    @Column(unique = true, name = "phone_number", nullable = false)
    @Pattern(regexp = "^3\\d{9}$", message = "Ingrese un numero de teléfono correcto")
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private RoleEntity role;

    @Column(nullable = false, name = "created_at")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDate createdAt;

    @Column(nullable = false, name = "last_session")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDate lastSession;

    @Column(name = "account_non_expired", nullable = false)
    private boolean AccountNonExpired;

    @Column(name = "account_non_locked", nullable = false)
    private boolean AccountNonLocked;

    @Column(name = "credentials_non_expired", nullable = false)
    private boolean CredentialsNonExpired;

    @Column(name = "enabled", nullable = false)
    private boolean Enabled;
}
