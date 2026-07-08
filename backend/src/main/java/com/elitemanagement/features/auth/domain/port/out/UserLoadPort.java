package com.elitemanagement.features.auth.domain.port.out;

import com.elitemanagement.features.auth.domain.model.AuthUser;
import java.util.Optional;

public interface UserLoadPort {
    Optional<AuthUser> loadByEmail(String email);
}
