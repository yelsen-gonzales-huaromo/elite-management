package com.elitemanagement.features.users.domain.model;

import com.elitemanagement.shared.domain.TenantId;
import com.elitemanagement.features.users.domain.exception.RoleUnauthorizedException;
import java.util.HashSet;
import java.util.Set;

public class User {
    private final String id;
    private final TenantId tenantId;
    private final String username;
    private final String email;
    private String passwordHash;
    private UserStatus status;
    private final Set<Role> roles;

    public User(String id, TenantId tenantId, String username, String email, String passwordHash, UserStatus status, Set<Role> roles) {
        this.id = id;
        this.tenantId = tenantId;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.status = status;
        this.roles = roles != null ? new HashSet<>(roles) : new HashSet<>();
    }

    public void assignRole(Role role) {
        if (!role.isGlobal() && !role.getTenantId().equals(this.tenantId)) {
            throw new RoleUnauthorizedException("Cannot assign a role belonging to another tenant");
        }
        this.roles.add(role);
    }

    public String getId() {
        return id;
    }

    public TenantId getTenantId() {
        return tenantId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void updatePasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public UserStatus getStatus() {
        return status;
    }

    public Set<Role> getRoles() {
        return Set.copyOf(roles);
    }
}
