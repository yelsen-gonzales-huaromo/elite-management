package com.elitemanagement.features.auth.domain.port.in;

public record LoginCommand(
    String email,
    String password
) {}
