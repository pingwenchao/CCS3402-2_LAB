package com.upm.lab9.repository;

import com.upm.lab9.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * PING WENCHAO 226969
 * ProjectRepository - Functional data broker supplying persistent operations for Project entities.
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {}