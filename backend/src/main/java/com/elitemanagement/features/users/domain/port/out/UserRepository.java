package com.elitemanagement.features.users.domain.port.out;

import com.elitemanagement.features.users.domain.model.User;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(String id);
    Optional<User> findByEmail(String email);
    boolean existsByEmailAndTenantId(String email, String tenantId);
}
