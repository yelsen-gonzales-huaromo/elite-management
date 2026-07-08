package com.elitemanagement.features.users.infrastructure.persistence;

import com.elitemanagement.shared.domain.TenantId;
import com.elitemanagement.features.users.domain.model.Role;
import com.elitemanagement.features.users.domain.port.out.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoleRepositoryAdapter implements RoleRepository {

    private final SpringDataRoleRepository jpaRepository;

    public RoleRepositoryAdapter(SpringDataRoleRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Role save(Role role) {
        RoleJpaEntity entity = toEntity(role);
        RoleJpaEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Role> findById(String id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Set<Role> findAllByIds(Set<String> ids) {
        List<RoleJpaEntity> entities = jpaRepository.findAllById(ids);
        return entities.stream().map(this::toDomain).collect(Collectors.toSet());
    }

    public static RoleJpaEntity toEntity(Role role) {
        if (role == null) return null;
        RoleJpaEntity entity = new RoleJpaEntity();
        entity.setId(role.getId());
        entity.setTenantId(role.getTenantId().value());
        entity.setName(role.getName());
        entity.setPermissions(new HashSet<>(role.getPermissions()));
        entity.setGlobal(role.isGlobal());
        return entity;
    }

    public Role toDomain(RoleJpaEntity entity) {
        if (entity == null) return null;
        return new Role(
            entity.getId(),
            new TenantId(entity.getTenantId()),
            entity.getName(),
            entity.getPermissions(),
            entity.isGlobal()
        );
    }
}
