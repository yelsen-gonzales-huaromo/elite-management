package com.elitemanagement.features.auth.domain.model;

public record Token(
    String accessToken,
    String refreshToken,
    String tokenType,
    long expiresIn
) {}
