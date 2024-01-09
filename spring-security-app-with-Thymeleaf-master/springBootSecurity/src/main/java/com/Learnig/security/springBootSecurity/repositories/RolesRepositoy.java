package com.Learnig.security.springBootSecurity.repositories;

import com.Learnig.security.springBootSecurity.entities.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RolesRepositoy extends JpaRepository<RolesEntity, Integer> {
    Optional<RolesEntity> findByRoleName(String roleName);
}
