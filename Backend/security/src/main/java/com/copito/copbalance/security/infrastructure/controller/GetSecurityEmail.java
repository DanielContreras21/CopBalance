package com.copito.copbalance.security.infrastructure.controller;

import com.copito.copbalance.security.application.dto.response.AccountEmail;
import com.copito.copbalance.security.domain.usecase.account.GetSecurityEmailUSeCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class GetSecurityEmail {

    private final GetSecurityEmailUSeCase service;

    @GetMapping("/getEmail/{id}")
    public ResponseEntity<AccountEmail> getSecurityEmail(@PathVariable String id){
        return ResponseEntity.ok(service.getEmail(id));
    }
}
