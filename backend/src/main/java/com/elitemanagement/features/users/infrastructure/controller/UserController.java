package com.elitemanagement.features.users.infrastructure.controller;

import com.elitemanagement.shared.domain.TenantId;
import com.elitemanagement.shared.db.TenantContext;
import com.elitemanagement.shared.web.ApiResponse;
import com.elitemanagement.features.users.application.dto.UserResponse;
import com.elitemanagement.features.users.application.mapper.UserMapper;
import com.elitemanagement.features.users.domain.model.User;
import com.elitemanagement.features.users.domain.port.in.CreateUserCommand;
import com.elitemanagement.features.users.domain.port.in.CreateUserUseCase;
import com.elitemanagement.features.users.domain.port.in.FindUserUseCase;
import com.elitemanagement.features.users.infrastructure.controller.dto.CreateUserRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final FindUserUseCase findUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase, FindUserUseCase findUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.findUserUseCase = findUserUseCase;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('write:users')")
    public ResponseEntity<ApiResponse<UserResponse>> create(@Valid @RequestBody CreateUserRequest request) {
        String activeTenant = TenantContext.getCurrentTenant();
        TenantId tenantId = new TenantId(activeTenant != null ? activeTenant : "public");

        CreateUserCommand command = new CreateUserCommand(
            tenantId,
            request.username(),
            request.email(),
            request.password(),
            request.roles()
        );

        User saved = createUserUseCase.createUser(command);
        UserResponse response = UserMapper.toResponse(saved);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('read:users')")
    public ResponseEntity<ApiResponse<UserResponse>> getById(@PathVariable String id) {
        return findUserUseCase.findById(id)
                .map(UserMapper::toResponse)
                .map(ApiResponse::success)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
