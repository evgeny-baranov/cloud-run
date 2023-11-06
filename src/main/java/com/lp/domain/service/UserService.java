package com.lp.domain.service;

import com.lp.domain.model.RoleEnum;
import com.lp.domain.model.User;
import com.lp.domain.model.UserRole;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    public User createUser(String name, String email);

    public void addUserRole(User user, RoleEnum role);

    Iterable<User> getAllUsers();

    public Iterable<UserRole> getAllRoles();

    public void initRoles();

    public void initOauthProviders();

    public Optional<User> findByEmail(String email);
}
