package com.lp.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "users", catalog = "testdb")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends AbstractEntity {

    private String name;

    @Column(unique = true)
    private String email;

    @ManyToOne(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.EAGER
    )
    private Status status;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private Collection<UserRole> roles = new HashSet<>();

    public boolean haveRole(Role role) {
        for (UserRole userRole : roles) {
            if (userRole.getRole().equals(role)) {
                return true;
            }
        }

        return false;
    }

    public boolean haveRole(RoleEnum roleName) {
        return haveRole(new Role(roleName));
    }

    public void addRole(Role role) {
        if (!haveRole(role)) {
            UserRole ur = new UserRole();
            ur.setUser(this);
            ur.setRole(role);
            roles.add(ur);
        }
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.status = new Status(StatusEnum.STATUS_PENDING);
    }

    public User(String name, String email, StatusEnum status) {
        this.name = name;
        this.email = email;
        this.status = new Status(status);
    }
}
