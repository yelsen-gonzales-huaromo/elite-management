package com.elitemanagement.features.users.application.service;

import com.elitemanagement.features.users.domain.model.Role;
import com.elitemanagement.features.users.domain.model.User;
import com.elitemanagement.features.users.domain.model.UserStatus;
import com.elitemanagement.features.users.domain.port.in.CreateUserCommand;
import com.elitemanagement.features.users.domain.port.in.CreateUserUseCase;
import com.elitemanagement.features.users.domain.port.out.PasswordEncoderPort;
import com.elitemanagement.features.users.domain.port.out.RoleRepository;
import com.elitemanagement.features.users.domain.port.out.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class CreateUserService implements CreateUserUseCase {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoderPort passwordEncoder;

    public CreateUserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoderPort passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(CreateUserCommand command) {
        if (userRepository.existsByEmailAndTenantId(command.email(), command.tenantId().value())) {
            throw new IllegalArgumentException("User with this email already exists in this tenant");
        }

        String passwordHash = passwordEncoder.encode(command.rawPassword());
        Set<Role> roles = roleRepository.findAllByIds(command.roleIds());

        User user = new User(
            UUID.randomUUID().toString(),
            command.tenantId(),
            command.username(),
            command.email(),
            passwordHash,
            UserStatus.ACTIVE,
            roles
        );

        return userRepository.save(user);
    }
}
