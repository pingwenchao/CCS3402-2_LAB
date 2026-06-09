package com.upm.lab9.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * PING WENCHAO 226969
 * Project - Entity representing technical assignments within the organization.
 * Defines the inverse side of the Many-to-Many relationship mapped to the Staff schema.
 */
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /**
     * Bidirectional relationship mapping established via inverse association in the owning entity.
     */
    @ManyToMany(mappedBy = "projects")
    private Set<Staff> staffs = new HashSet<>();

    // Default Constructor
    public Project() {}

    // Standard Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Set<Staff> getStaffs() { return staffs; }
    public void setStaffs(Set<Staff> staffs) { this.staffs = staffs; }
}