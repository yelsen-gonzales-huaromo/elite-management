package com.elitemanagement.features.auth.domain.port.out;

import com.elitemanagement.features.auth.domain.model.Token;
import java.util.Set;

public interface TokenGeneratorPort {
    Token generate(String userId, String tenantId, String username, String email, Set<String> roles);
}
