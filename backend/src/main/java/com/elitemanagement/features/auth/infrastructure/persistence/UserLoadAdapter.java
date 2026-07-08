package com.elitemanagement.features.auth.infrastructure.persistence;

import com.elitemanagement.features.auth.domain.model.AuthUser;
import com.elitemanagement.features.auth.domain.port.out.UserLoadPort;
import com.elitemanagement.features.users.domain.port.in.FindUserUseCase;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserLoadAdapter implements UserLoadPort {

    private final FindUserUseCase findUserUseCase;

    public UserLoadAdapter(FindUserUseCase findUserUseCase) {
        this.findUserUseCase = findUserUseCase;
    }

    @Override
    public Optional<AuthUser> loadByEmail(String email) {
        return findUserUseCase.findByEmail(email)
                .map(user -> new AuthUser(
                        user.getId(),
                        user.getTenantId().value(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getPasswordHash(),
                        user.getStatus().name(),
                        user.getRoles().stream()
                                .flatMap(role -> role.getPermissions().stream())
                                .map(permission -> permission.getAuthority())
                                .collect(Collectors.toSet())
                ));
    }
}
