package com.elitemanagement.features.users.infrastructure.persistence;

import com.elitemanagement.shared.domain.TenantId;
import com.elitemanagement.features.users.domain.model.User;
import com.elitemanagement.features.users.domain.port.out.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserRepositoryAdapter implements UserRepository {

    private final SpringDataUserRepository jpaRepository;

    public UserRepositoryAdapter(SpringDataUserRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public User save(User user) {
        UserJpaEntity entity = toEntity(user);
        UserJpaEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<User> findById(String id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmail(email).map(this::toDomain);
    }

    @Override
    public boolean existsByEmailAndTenantId(String email, String tenantId) {
        return jpaRepository.existsByEmailAndTenantId(email, tenantId);
    }

    private UserJpaEntity toEntity(User user) {
        if (user == null) return null;
        UserJpaEntity entity = new UserJpaEntity();
        entity.setId(user.getId());
        entity.setTenantId(user.getTenantId().value());
        entity.setUsername(user.getUsername());
        entity.setEmail(user.getEmail());
        entity.setPasswordHash(user.getPasswordHash());
        entity.setStatus(user.getStatus());
        entity.setRoles(user.getRoles().stream()
            .map(RoleRepositoryAdapter::toEntity)
            .collect(Collectors.toSet()));
        return entity;
    }

    private User toDomain(UserJpaEntity entity) {
        if (entity == null) return null;
        RoleRepositoryAdapter roleAdapter = new RoleRepositoryAdapter(null);
        return new User(
            entity.getId(),
            new TenantId(entity.getTenantId()),
            entity.getUsername(),
            entity.getEmail(),
            entity.getPasswordHash(),
            entity.getStatus(),
            entity.getRoles().stream().map(roleAdapter::toDomain).collect(Collectors.toSet())
        );
    }
}
