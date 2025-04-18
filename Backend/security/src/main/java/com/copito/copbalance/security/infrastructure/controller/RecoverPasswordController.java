package com.copito.copbalance.security.infrastructure.controller;

import com.copito.copbalance.security.domain.model.dto.request.RecoverPasswordRequest;
import com.copito.copbalance.security.domain.usecase.RecoverPasswordUseCase;
import jakarta.ws.rs.QueryParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class RecoverPasswordController {
    private final RecoverPasswordUseCase useCase;

    @PatchMapping("/recoverPassword")
    private final ResponseEntity<String> recoverPassword(@RequestParam("random") String random, @RequestBody RecoverPasswordRequest request){
        useCase.updatePassword(random, request);
        return ResponseEntity.ok("Contraseña cambiada correctamente");
    }
}
