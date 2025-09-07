package com.copito.copbalance.security.infrastructure.controller;

import com.copito.copbalance.security.application.dto.request.RegisterRequest;
import com.copito.copbalance.security.application.dto.response.RegisterResponse;
import com.copito.copbalance.security.domain.usecase.account.RegisterUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class RegisterController {
    private final RegisterUseCase useCase;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request){
        RegisterResponse register = useCase.register(request);
        return ResponseEntity.ok(register);
    }
}
