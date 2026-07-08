package com.elitemanagement.features.users.application.dto;

import java.util.Set;

public record UserResponse(
    String id,
    String tenantId,
    String username,
    String email,
    String status,
    Set<String> roles
) {}
