package com.upm.lab10.repository;

import com.upm.lab10.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * PING WENCHAO 226969
 * UserRepository - Functional data broker supplying persistent operations for User entities.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Retrieves a distinct user entity based on their unique email address.
     */
    User findByEmail(String email);
}