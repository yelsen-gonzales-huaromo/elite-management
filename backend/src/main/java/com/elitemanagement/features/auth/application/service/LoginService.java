package com.elitemanagement.features.auth.application.service;

import com.elitemanagement.features.auth.domain.model.AuthUser;
import com.elitemanagement.features.auth.domain.model.Token;
import com.elitemanagement.features.auth.domain.port.in.LoginCommand;
import com.elitemanagement.features.auth.domain.port.in.LoginUseCase;
import com.elitemanagement.features.auth.domain.port.out.PasswordEncoderPort;
import com.elitemanagement.features.auth.domain.port.out.TokenGeneratorPort;
import com.elitemanagement.features.auth.domain.port.out.UserLoadPort;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginUseCase {

    private final UserLoadPort userLoadPort;
    private final PasswordEncoderPort passwordEncoder;
    private final TokenGeneratorPort tokenGenerator;

    public LoginService(UserLoadPort userLoadPort, PasswordEncoderPort passwordEncoder, TokenGeneratorPort tokenGenerator) {
        this.userLoadPort = userLoadPort;
        this.passwordEncoder = passwordEncoder;
        this.tokenGenerator = tokenGenerator;
    }

    @Override
    public Token login(LoginCommand command) {
        AuthUser user = userLoadPort.loadByEmail(command.email())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        if (!"ACTIVE".equals(user.status())) {
            throw new IllegalStateException("User account is not active");
        }

        if (!passwordEncoder.matches(command.password(), user.passwordHash())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        return tokenGenerator.generate(
            user.id(),
            user.tenantId(),
            user.username(),
            user.email(),
            user.roles()
        );
    }
}
