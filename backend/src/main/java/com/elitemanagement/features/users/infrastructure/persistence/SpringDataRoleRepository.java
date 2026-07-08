package com.elitemanagement.features.users.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataRoleRepository extends JpaRepository<RoleJpaEntity, String> {
}
