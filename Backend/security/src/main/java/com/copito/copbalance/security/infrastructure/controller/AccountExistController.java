package com.copito.copbalance.security.infrastructure.controller;

import com.copito.copbalance.security.application.dto.request.AccountExistRequest;
import com.copito.copbalance.security.domain.usecase.account.AccountExistUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AccountExistController {

    private final AccountExistUseCase useCase;

    @PostMapping("/accountExist")
    public ResponseEntity<String> accountExist(@RequestBody AccountExistRequest request){
        useCase.exist(request);
        return ResponseEntity.ok("El correo y contraseña están disponibles");
    }
}
