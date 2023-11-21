package com.lp.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", catalog = "testdb")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends AbstractUuidEntity {

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @ManyToOne(
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER
    )
    private Status status;

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setStatus(StatusEnum statusName) {
        setStatus(new Status(statusName));
    }

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "user",
            fetch = FetchType.EAGER
    )
    private Set<UserRole> roles = new HashSet<>();

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

    public void addRole(RoleEnum roleName) {
        addRole(new Role(roleName));
    }

    public void removeRole(RoleEnum roleName) {
        removeRole(new Role(roleName));
    }

    public void removeRole(Role role) {
        roles.stream()
                .filter(userRole -> userRole.getRole().equals(role))
                .toList()
                .forEach(roles::remove);
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
