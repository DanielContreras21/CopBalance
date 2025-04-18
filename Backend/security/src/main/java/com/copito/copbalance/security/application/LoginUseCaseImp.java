package com.copito.copbalance.security.application;

import com.copito.copbalance.security.domain.model.dto.request.LoginRequest;
import com.copito.copbalance.security.domain.usecase.LoginUseCase;
import com.copito.copbalance.security.infrastructure.security.JwtUtils;
import com.copito.copbalance.security.infrastructure.security.UserDetailsServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LoginUseCaseImp implements LoginUseCase {
    private final JwtUtils jwtUtils;
    private final UserDetailsServiceImp userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String login(LoginRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();

        if (email.isBlank()) {
            throw new NoSuchElementException("Ingrese un nombre de usuario válido");
        }
        if (password.isBlank()) {
            throw new NoSuchElementException("Ingrese una contraseña válida");
        }
        Authentication authentication = this.authenticate(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtUtils.createToken(authentication);
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        if (userDetails == null){
            throw new IllegalArgumentException("Nombre de usuario o contraseña incorrectos");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new IllegalArgumentException("Nombre de usuario o contraseña incorrectos");
        }
        return new UsernamePasswordAuthenticationToken(email, userDetails.getPassword(), userDetails.getAuthorities());
    }
}
