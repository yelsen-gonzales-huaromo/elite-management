package com.elitemanagement.features.auth.infrastructure.controller.dto;

public record TokenResponse(
    String accessToken,
    String refreshToken,
    String tokenType,
    long expiresIn
) {}
