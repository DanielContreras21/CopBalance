package com.copito.copbalance.security.infrastructure.controller;

import com.copito.copbalance.security.domain.model.dto.request.AccountRequest;
import com.copito.copbalance.security.domain.usecase.UpdateAccountUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UpdateAccountController {
    private final UpdateAccountUseCase useCase;

    @PatchMapping("/update/account/{id}")
    public ResponseEntity<String> updateAccount(@PathVariable String id, @RequestBody AccountRequest request){
        useCase.update(id, request);
        return ResponseEntity.ok("Usuario actualizado correctamente");
    }
}
