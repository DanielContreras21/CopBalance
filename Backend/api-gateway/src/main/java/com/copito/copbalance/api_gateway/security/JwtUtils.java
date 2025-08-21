package com.copito.copbalance.api_gateway.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class JwtUtils {
    @Value("${security.jwt.key}")
    private String privateKey;

    @Value("${security.jwt.user}")
    private String userGenerator;


    public DecodedJWT validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(privateKey);

            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(userGenerator)
                    .build();

            return verifier.verify(token);
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
