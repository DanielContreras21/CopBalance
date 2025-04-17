package com.copito.copbalance.security.infrastructure.controller;

import com.copito.copbalance.security.domain.usecase.SendActivationMailUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class SendActivationMailController {
    private final SendActivationMailUseCase useCase;

    @PostMapping("/sendActivation/{id}")
    public ResponseEntity<String> sendActivationLink(@PathVariable String id){
        useCase.send(id);
        return ResponseEntity.ok("Link de activación enviado correctamente");
    }
}
