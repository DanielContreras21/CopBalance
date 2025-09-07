package com.copito.copbalance.security.infrastructure.controller;

import com.copito.copbalance.security.application.dto.request.UpdatePasswordRequest;
import com.copito.copbalance.security.domain.usecase.account.UpdatePasswordUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UpdatePasswordController {
    private final UpdatePasswordUseCase useCase;

    @PatchMapping("/update/password/{id}")
    public ResponseEntity<String> updatePassword(@PathVariable String id, @Valid @RequestBody UpdatePasswordRequest request){
        useCase.updatePassword(id, request);
        return ResponseEntity.ok("Contraseña Actualizada Correctamente");
    }
}
