package com.copito.copbalance.user.infrastructure.controller;

import com.copito.copbalance.user.domain.model.entity.User;
import com.copito.copbalance.user.domain.usecase.CompleteUserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class CompleteUserProfileController {

    private final CompleteUserProfile useCase;

    @PostMapping("/completeUserProfile")
    public ResponseEntity<String> completeUserProfile(@RequestBody User user){
        useCase.complete(user);
        return ResponseEntity.ok("Se ha completado el registro");
    }
}
