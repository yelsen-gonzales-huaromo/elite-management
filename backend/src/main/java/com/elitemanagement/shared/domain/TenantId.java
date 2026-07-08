package com.elitemanagement.shared.domain;

import java.io.Serializable;
import java.util.UUID;

public record TenantId(String value) implements Serializable {
    public TenantId {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Tenant ID cannot be null or empty");
        }
    }

    public static TenantId generate() {
        return new TenantId(UUID.randomUUID().toString());
    }
}
