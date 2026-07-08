package com.elitemanagement.features.users.domain.port.in;

import com.elitemanagement.features.users.domain.model.User;
import java.util.Optional;

public interface FindUserUseCase {
    Optional<User> findById(String id);
    Optional<User> findByEmail(String email);
}
