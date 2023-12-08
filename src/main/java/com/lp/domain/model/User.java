package com.lp.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(
        callSuper = true,
        exclude = {"status", "roles"}
)
@Entity
@Getter
@Setter
@NoArgsConstructor
public class User extends AbstractUuidEntity implements UserDetails {

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @ManyToOne(
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER
    )
    @ToString.Exclude
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
    @ToString.Exclude
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
        if (roles.stream().noneMatch(userRole -> userRole.getRole().equals(role))) {
            UserRole userRole = new UserRole();
            userRole.setUser(this);
            userRole.setRole(role);
            roles.add(userRole);
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles().stream().map(role -> new SimpleGrantedAuthority(
                role.getRole().getName().name()
        )).toList();
    }

    @Override
    public String getPassword() {
        return "null";
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        // todo: use status
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // todo: use status
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // todo: use status
        return true;
    }

    @Override
    public boolean isEnabled() {
        // todo: use status
        return true;
    }
}
