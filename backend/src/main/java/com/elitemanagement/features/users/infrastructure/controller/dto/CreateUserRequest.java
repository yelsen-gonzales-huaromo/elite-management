package com.elitemanagement.features.users.infrastructure.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public record CreateUserRequest(
    @NotBlank String username,
    @NotBlank @Email String email,
    @NotBlank String password,
    @NotEmpty Set<String> roles
) {}
