package com.elitemanagement.features.users.domain.exception;

import com.elitemanagement.shared.domain.DomainException;

public class RoleUnauthorizedException extends DomainException {
    public RoleUnauthorizedException(String message) {
        super("ROLE_UNAUTHORIZED", message);
    }
}
