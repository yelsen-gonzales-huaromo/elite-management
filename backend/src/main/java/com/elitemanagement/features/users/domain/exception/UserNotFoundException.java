package com.elitemanagement.features.users.domain.exception;

import com.elitemanagement.shared.domain.DomainException;

public class UserNotFoundException extends DomainException {
    public UserNotFoundException(String message) {
        super("USER_NOT_FOUND", message);
    }
}
