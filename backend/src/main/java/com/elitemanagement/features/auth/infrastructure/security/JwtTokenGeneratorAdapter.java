package com.elitemanagement.features.auth.infrastructure.security;

import com.elitemanagement.features.auth.domain.model.Token;
import com.elitemanagement.features.auth.domain.port.out.TokenGeneratorPort;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Component
public class JwtTokenGeneratorAdapter implements TokenGeneratorPort {

    private final String secret;
    private final long expirationMs;

    public JwtTokenGeneratorAdapter(
            @Value("${app.security.jwt.secret:default-very-secure-secret-key-of-at-least-256-bits-length-must-be-long-enough}") String secret,
            @Value("${app.security.jwt.expiration-ms:3600000}") long expirationMs) {
        this.secret = secret;
        this.expirationMs = expirationMs;
    }

    @Override
    public Token generate(String userId, String tenantId, String username, String email, Set<String> roles) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationMs);

        String jwt = Jwts.builder()
                .subject(userId)
                .claim("tenantId", tenantId)
                .claim("username", username)
                .claim("email", email)
                .claim("roles", roles)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(key)
                .compact();

        String refreshToken = UUID.randomUUID().toString();

        return new Token(jwt, refreshToken, "Bearer", expirationMs / 1000);
    }
}
