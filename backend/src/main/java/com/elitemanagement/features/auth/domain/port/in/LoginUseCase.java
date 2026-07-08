package com.elitemanagement.features.auth.domain.port.in;

import com.elitemanagement.features.auth.domain.model.Token;

public interface LoginUseCase {
    Token login(LoginCommand command);
}
