package com.elitemanagement.features.users.domain.port.in;

import com.elitemanagement.shared.domain.TenantId;
import java.util.Set;

public record CreateUserCommand(
    TenantId tenantId,
    String username,
    String email,
    String rawPassword,
    Set<String> roleIds
) {
    public CreateUserCommand {
        if (tenantId == null) throw new IllegalArgumentException("Tenant ID is required");
        if (username == null || username.trim().isEmpty()) throw new IllegalArgumentException("Username is required");
        if (email == null || email.trim().isEmpty()) throw new IllegalArgumentException("Email is required");
        if (rawPassword == null || rawPassword.trim().isEmpty()) throw new IllegalArgumentException("Password is required");
    }
}
