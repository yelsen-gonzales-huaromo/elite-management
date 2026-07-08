package com.elitemanagement.features.users.application.service;

import com.elitemanagement.features.users.domain.model.User;
import com.elitemanagement.features.users.domain.port.in.FindUserUseCase;
import com.elitemanagement.features.users.domain.port.out.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class FindUserService implements FindUserUseCase {

    private final UserRepository userRepository;

    public FindUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
