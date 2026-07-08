package com.elitemanagement.features.auth.infrastructure.controller;

import com.elitemanagement.features.auth.domain.model.Token;
import com.elitemanagement.features.auth.domain.port.in.LoginCommand;
import com.elitemanagement.features.auth.domain.port.in.LoginUseCase;
import com.elitemanagement.features.auth.infrastructure.controller.dto.LoginRequest;
import com.elitemanagement.features.auth.infrastructure.controller.dto.TokenResponse;
import com.elitemanagement.shared.web.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final LoginUseCase loginUseCase;

    public AuthController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<TokenResponse>> login(@Valid @RequestBody LoginRequest request) {
        LoginCommand command = new LoginCommand(request.email(), request.password());
        Token token = loginUseCase.login(command);
        TokenResponse response = new TokenResponse(
            token.accessToken(),
            token.refreshToken(),
            token.tokenType(),
            token.expiresIn()
        );
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
