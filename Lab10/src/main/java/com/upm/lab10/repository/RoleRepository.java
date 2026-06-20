package com.upm.lab10.repository;

import com.upm.lab10.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * PING WENCHAO 226969
 * RoleRepository - Functional data broker supplying persistent operations for Role entities.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Retrieves a specific authorization role based on its string identifier.
     */
    Role findByName(String name);
}