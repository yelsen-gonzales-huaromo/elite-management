package com.elitemanagement.features.auth.domain.port.out;

public interface PasswordEncoderPort {
    boolean matches(CharSequence rawPassword, String encodedPassword);
}
