package com.upm.lab9.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * PING WENCHAO 226969
 * Staff - Core relational hub maintaining associations across corporate departments and portfolios.
 * Acts as the owning side for both Many-to-One and Many-to-Many system structures.
 */
@Entity
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    /**
     * Many-to-One relationship defining institutional department alignment.
     * Generates a concrete foreign key constraint named 'department_id' in the backend schema.
     */
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    /**
     * Many-to-Many configuration mapping operational project portfolios.
     * Instructs Hibernate to synthesize the structural 'staff_project' cross-reference join table.
     */
    @ManyToMany
    @JoinTable(
            name = "staff_project",
            joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private Set<Project> projects = new HashSet<>();

    // Default Constructor
    public Staff() {}

    // Standard Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
    public Set<Project> getProjects() { return projects; }
    public void setProjects(Set<Project> projects) { this.projects = projects; }
}