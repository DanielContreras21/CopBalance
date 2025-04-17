package com.copito.copbalance.security.infrastructure.controller;

import com.copito.copbalance.security.domain.usecase.ActivateAccountUseCase;
import jakarta.mail.MessagingException;
import jakarta.ws.rs.QueryParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class ActivateAccountController {
    private final ActivateAccountUseCase useCase;

    @GetMapping("/activate")
    public ResponseEntity<String> sendEmail(@QueryParam("random") String random) {
        useCase.activate(random);
        return ResponseEntity.ok("Cuenta activada existosamente");
    }
}
