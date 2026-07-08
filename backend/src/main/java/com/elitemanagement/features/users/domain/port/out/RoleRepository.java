package com.elitemanagement.features.users.domain.port.out;

import com.elitemanagement.features.users.domain.model.Role;
import java.util.Optional;
import java.util.Set;

public interface RoleRepository {
    Role save(Role role);
    Optional<Role> findById(String id);
    Set<Role> findAllByIds(Set<String> ids);
}
