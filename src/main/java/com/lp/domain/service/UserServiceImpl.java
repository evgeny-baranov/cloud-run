package com.lp.domain.service;

import com.lp.domain.model.*;
import com.lp.domain.repository.RoleRepository;
import com.lp.domain.repository.UserRepository;
import com.lp.domain.repository.UserRoleRepository;
import com.lp.domain.repository.UserStatusRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final UserRoleRepository userRoleRepository;

    private final UserStatusRepository userStatusRepository;

    public UserServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            UserRoleRepository userRoleRepository,
            UserStatusRepository userStatusRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.userStatusRepository = userStatusRepository;
    }

    public User saveUser(User user) {
        if (user.getStatus().getId() == null) {
            userStatusRepository.findByName(
                    user.getStatus().getName()
            ).ifPresent(user::setStatus);
        }

        user.getRoles().forEach(userRole -> {
            if (userRole.getRole().getId() == null) {
                roleRepository.findByName(
                        userRole.getRole().getName()
                ).ifPresent(userRole::setRole);
            }
        });

        return userRepository.save(user);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Iterable<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Iterable<Status> getAllStatuses() {
        return userStatusRepository.findAll();
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @PostConstruct
    public void init() {
        initRoles();
        initUserStatuses();
    }

    public void initRoles() {
        Arrays.stream(RoleEnum.values()).forEach(roleName -> {
            if (this.roleRepository.findByName(roleName).isEmpty()) {
                this.roleRepository.save(
                        new Role(roleName)
                );
            }
        });
    }

    public void initUserStatuses() {
        Arrays.stream(StatusEnum.values()).forEach(statusName -> {
            if (userStatusRepository.findByName(statusName).isEmpty()) {
                this.userStatusRepository.save(
                        new Status(statusName)
                );
            }
        });
    }
}
