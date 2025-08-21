package com.copito.copbalance.security.infrastructure.controller;

import com.copito.copbalance.security.application.dto.request.LoginRequest;
import com.copito.copbalance.security.domain.usecase.LoginUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {

    private final LoginUseCase useCase;

    @PostMapping("/login")
    public ResponseEntity<String> register(@Valid @RequestBody LoginRequest request){
        String login = useCase.login(request);
        return ResponseEntity.ok(login);
    }
}
