package com.upm.lab9.repository;

import com.upm.lab9.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * PING WENCHAO 226969
 * StaffRepository - Functional data broker supplying persistent operations for Staff entities.
 */
@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {}