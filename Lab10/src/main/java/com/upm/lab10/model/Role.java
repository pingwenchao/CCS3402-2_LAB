package com.upm.lab10.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * PING WENCHAO 226969
 * Role - Entity representing the authorization levels within the system context.
 * Defines the inverse side of the Many-to-Many relationship mapped to the User schema.
 */
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    // Bidirectional relationship mapping established via inverse association in the owning entity.
    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();

    // Default Constructor
    public Role() {}

    public Role(String name) {
        this.name = name;
    }

    public Role(String name, List<User> users) {
        this.name = name;
        this.users = users;
    }

    // Standard Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<User> getUsers() { return users; }
    public void setUsers(List<User> users) { this.users = users; }
}