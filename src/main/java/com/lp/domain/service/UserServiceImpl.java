package com.lp.domain.service;

import com.lp.domain.model.*;
import com.lp.domain.repository.RoleRepository;
import com.lp.domain.repository.StatusRepository;
import com.lp.domain.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Log
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final GenericCache<Status, StatusEnum, StatusRepository> statusCache;

    private final GenericCache<Role, RoleEnum, RoleRepository> roleCache;

    private final StatusRepository statusRepository;

    public UserServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            GenericCache<Role, RoleEnum, RoleRepository> roleCache,
            StatusRepository userStatusRepository,
            GenericCache<Status, StatusEnum, StatusRepository> statusCache
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.statusRepository = userStatusRepository;
        this.statusCache = statusCache;
        this.roleCache = roleCache;
    }

    public User saveUser(User user) {
        Status status = statusCache.get(
                user.getStatus().getName()
        ).orElseGet(() -> {
            Status newStatus = statusRepository.save(
                    new Status(user.getStatus().getName()
                    )
            );
            statusCache.add(newStatus);
            return newStatus;
        });

        user.setStatus(status);

        Collection<UserRole> roles = new HashSet<>(user.getRoles());
        user.setRoles(new HashSet<>());

        roles.forEach(userRole -> {
            roleCache.get(
                    userRole.getRole().getName()
            ).ifPresent(userRole::setRole);
            user.getRoles().add(userRole);
        });

        return userRepository.save(user);
    }

    public Page<User> getAllUsers(
            int page,
            int size,
            String sortBy,
            String direction
    ) {
        return userRepository.findAll(
                PageRequest.of(
                        page - 1,
                        size,
                        Sort.by(
                                Sort.Direction.fromString(direction),
                                sortBy
                        )
                )
        );
    }

    public Iterable<Role> getAllRoles() {
        return roleCache.getAll();
    }

    public Iterable<Status> getAllStatuses() {
        return statusCache.getAll();
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findById(UUID uuid) {
        return userRepository.findByUuid(uuid);
    }

    @PostConstruct
    public void init() {
        try {
            initRoles();
            initStatuses();
            initSystemUser();
        } catch (Throwable error) {
            log.info(error.getMessage());
        }
    }

    public void initSystemUser() {
        // TODO: save system users to secrets
        User user = new User(
                "system",
                "info@baranov.eu"
        );
        user.setStatus(StatusEnum.STATUS_ACTIVE);
        Arrays.stream(RoleEnum.values()).forEach(user::addRole);
        saveUser(user);
    }

    public void initRoles() {
        Arrays.stream(RoleEnum.values()).forEach(roleName -> {
            if (roleRepository.findByName(roleName).isEmpty()) {
                roleRepository.save(
                        new Role(roleName)
                );
            }
        });

        roleCache.refresh();
    }

    public void initStatuses() {
        Arrays.stream(StatusEnum.values()).forEach(statusName -> {
            if (statusRepository.findByName(statusName).isEmpty()) {
                this.statusRepository.save(
                        new Status(statusName)
                );
            }
        });

        statusCache.refresh();
    }
}
