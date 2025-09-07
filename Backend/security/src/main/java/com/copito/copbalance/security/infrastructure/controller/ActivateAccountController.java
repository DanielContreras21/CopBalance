package com.copito.copbalance.security.infrastructure.controller;

import com.copito.copbalance.security.domain.usecase.account.ActivateAccountUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class ActivateAccountController {
    private final ActivateAccountUseCase useCase;

    @PatchMapping("/activate")
    public ResponseEntity<String> sendEmail(@RequestParam("random") String random) {
        useCase.activate(random);
        return ResponseEntity.ok("Cuenta activada existosamente");
    }
}
