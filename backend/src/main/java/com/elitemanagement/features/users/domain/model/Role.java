package com.elitemanagement.features.users.domain.model;

import com.elitemanagement.shared.domain.TenantId;
import java.util.Set;

public class Role {
    private final String id;
    private final TenantId tenantId;
    private final String name;
    private final Set<Permission> permissions;
    private final boolean isGlobal;

    public Role(String id, TenantId tenantId, String name, Set<Permission> permissions, boolean isGlobal) {
        this.id = id;
        this.tenantId = tenantId;
        this.name = name;
        this.permissions = permissions;
        this.isGlobal = isGlobal;
    }

    public String getId() {
        return id;
    }

    public TenantId getTenantId() {
        return tenantId;
    }

    public String getName() {
        return name;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public boolean isGlobal() {
        return isGlobal;
    }
}
