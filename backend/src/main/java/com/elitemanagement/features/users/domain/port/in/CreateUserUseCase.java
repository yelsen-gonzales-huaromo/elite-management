package com.elitemanagement.features.users.domain.port.in;

import com.elitemanagement.features.users.domain.model.User;

public interface CreateUserUseCase {
    User createUser(CreateUserCommand command);
}
