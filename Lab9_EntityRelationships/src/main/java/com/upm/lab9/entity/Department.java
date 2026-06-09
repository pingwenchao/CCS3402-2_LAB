package com.upm.lab9.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * PING WENCHAO 226969
 * Department - Entity representing the structural units within the enterprise context.
 * Defines the 'One' side of the One-to-Many bidirectional relationship mapped to Staff.
 */
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /**
     * Bidirectional mapping setup controlled by the 'department' attribute in the Staff entity.
     * CascadeType.ALL ensures operational synchronization across relational entities.
     */
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private Set<Staff> staffs = new HashSet<>();

    // Default Constructor
    public Department() {}

    // Standard Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Set<Staff> getStaffs() { return staffs; }
    public void setStaffs(Set<Staff> staffs) { this.staffs = staffs; }
}