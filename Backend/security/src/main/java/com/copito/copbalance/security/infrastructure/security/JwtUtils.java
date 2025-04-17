package com.copito.copbalance.security.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.copito.copbalance.security.domain.model.entity.Account;
import com.copito.copbalance.security.domain.repository.AccountRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtUtils {
    @Value("${security.jwt.key}")
    private String privateKey;

    @Value("${security.jwt.user}")
    private String userGenerator;

    @Autowired
    private AccountRepositoryPort repository;


    public String createToken(Authentication authentication){
        Algorithm algorithm = Algorithm.HMAC256(privateKey);

        String email = authentication.getPrincipal().toString();
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));


        Optional<Account> account = repository.findByEmail(email);

        if (!account.isPresent()){
            account = repository.findByEmail(email);
        }

        Account accountEntity = account.get();

        String userId = accountEntity.getId();

        return JWT.create().withIssuer(userGenerator)
                .withSubject(email)
                .withClaim("authorities", authorities)
                .withClaim("userId", userId)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 18000000))
                .withJWTId(UUID.randomUUID().toString())
                .withNotBefore(new Date(System.currentTimeMillis()))
                .sign(algorithm);
    }


    public DecodedJWT validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(privateKey);

            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(userGenerator)
                    .build();

            DecodedJWT decodedJWT = verifier.verify(token);

            return decodedJWT;
        }
        catch (JWTVerificationException exception){
            throw new JWTVerificationException("Invalid Token");
        }
    }

    public String extractUserId(DecodedJWT decodedJWT) {
        return decodedJWT.getClaim("userId").asString();
    }

    public String extractUsername(DecodedJWT decodedJWt){
        return decodedJWt.getSubject();
    }

    public Claim getClaim(DecodedJWT decodedJWT, String claimName){
        return decodedJWT.getClaim(claimName);
    }

    public Map<String, Claim> getAllClaims(DecodedJWT decodedJWT){
        return decodedJWT.getClaims();
    }
}
