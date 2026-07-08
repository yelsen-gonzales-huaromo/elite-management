package com.elitemanagement.features.users.domain.model;

public enum Permission {
    READ_USERS("read:users"),
    WRITE_USERS("write:users"),
    DELETE_USERS("delete:users"),
    MANAGE_ROLES("manage:roles");

    private final String authority;

    Permission(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }
}
