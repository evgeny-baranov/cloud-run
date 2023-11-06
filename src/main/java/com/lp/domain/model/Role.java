package com.lp.domain.model;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role", catalog = "testdb")
public class Role extends AbstractEntity {
    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    @OneToMany(
            mappedBy = "role",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<UserRole> userRoles = new HashSet<>();

    public RoleEnum getName() {
        return name;
    }

    public void setName(RoleEnum name) {
        this.name = name;
    }
}
