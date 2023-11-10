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
public class User extends AbstractEntity {

    private String name;

    private String email;

    @ManyToOne(
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER
    )
    private Status status;

    @OneToMany(
            mappedBy = "user",
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            fetch = FetchType.EAGER
    )
    private Set<UserRole> roles = new HashSet<>();

    public void addRole(Role role) {
        UserRole ur = new UserRole();
        ur.setUser(this);
        ur.setRole(role);
        roles.add(ur);
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
