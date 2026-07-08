package com.elitemanagement.features.users.application.mapper;

import com.elitemanagement.features.users.application.dto.UserResponse;
import com.elitemanagement.features.users.domain.model.Role;
import com.elitemanagement.features.users.domain.model.User;

import java.util.stream.Collectors;

public final class UserMapper {

    private UserMapper() {}

    public static UserResponse toResponse(User user) {
        if (user == null) {
            return null;
        }
        return new UserResponse(
            user.getId(),
            user.getTenantId().value(),
            user.getUsername(),
            user.getEmail(),
            user.getStatus().name(),
            user.getRoles().stream().map(Role::getName).collect(Collectors.toSet())
        );
    }
}
