package com.upm.lab9.repository;

import com.upm.lab9.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * PING WENCHAO 226969
 * DepartmentRepository - Functional data broker supplying persistent operations for Department entities.
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {}