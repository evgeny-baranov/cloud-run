package com.lp.domain.service;

import com.lp.domain.model.*;
import com.lp.domain.repository.OauthProviderRepository;
import com.lp.domain.repository.RoleRepository;
import com.lp.domain.repository.UserRepository;
import com.lp.domain.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final OauthProviderRepository oauthProviderRepository;

    private final RoleRepository roleRepository;

    @Autowired
    private final UserRoleRepository userRoleRepository;

    public UserServiceImpl(
            UserRepository userRepository,
            OauthProviderRepository oauthProviderRepository,
            RoleRepository roleRepository,
            UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.oauthProviderRepository = oauthProviderRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
    }

    public User createUser(String name, String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        return userRepository.save(user);
    }

    @Override
    public void addUserRole(User user, RoleEnum roleName) {
        System.out.println("addUserRole: " + roleName);
        Optional<Role> optionalRole = roleRepository.findByName(roleName);

        Role role;

        if (optionalRole.isEmpty()) {
            role = new Role();
            role.setName(roleName);
            this.roleRepository.save(role);
        } else {
            role = optionalRole.get();
        }

        user.addRole(role);
        userRepository.save(user);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Iterable<UserRole> getAllRoles() {
        return userRoleRepository.findAll();
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void initRoles() {
        Arrays.stream(RoleEnum.values()).forEach(roleName -> {
            Role role = new Role();
            role.setName(roleName);
            this.roleRepository.save(role);
        });
    }

    public void initOauthProviders() {
        oauthProviderRepository.save(new OauthProvider("Google"));
        oauthProviderRepository.save(new OauthProvider("GitHub"));
        oauthProviderRepository.save(new OauthProvider("Facebook"));
    }
}
