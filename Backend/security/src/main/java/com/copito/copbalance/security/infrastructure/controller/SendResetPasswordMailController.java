package com.copito.copbalance.security.infrastructure.controller;

import com.copito.copbalance.security.domain.usecase.SendResetPasswordMailUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class SendResetPasswordMailController {
    private final SendResetPasswordMailUseCase useCase;

    @PatchMapping("/sendReset/{id}")
    public ResponseEntity<String> sendResetLink(@PathVariable String id){
        useCase.send(id);
        return ResponseEntity.ok("Link de recuperación de contraseña enviado");
    }
}
