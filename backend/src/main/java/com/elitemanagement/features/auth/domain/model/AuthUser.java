package com.elitemanagement.features.auth.domain.model;

import java.util.Set;

public record AuthUser(
    String id,
    String tenantId,
    String username,
    String email,
    String passwordHash,
    String status,
    Set<String> roles
) {}
