package com.lp.domain.service;

import com.lp.domain.model.*;
import com.lp.domain.repository.RoleRepository;
import com.lp.domain.repository.StatusRepository;
import com.lp.domain.repository.UserRepository;
import com.lp.domain.repository.UserRoleRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
@Log
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    private final RoleRepository roleRepository;

    private final UserRoleRepository userRoleRepository;

    private final StatusRepository statusRepository;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            UserRoleRepository userRoleRepository,
            StatusRepository userStatusRepository
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.statusRepository = userStatusRepository;
    }

    @Transactional
    public User saveUser(User user) {
        statusRepository.findByName(user.getStatus().getName()).ifPresentOrElse(
                user::setStatus,
                () -> {
                    Status newStatus = new Status(user.getStatus().getName());
                    statusRepository.save(newStatus);
                    user.setStatus(newStatus);
                }
        );

        user.getRoles().forEach(userRole -> {
            roleRepository.findByName(
                    userRole.getRole().getName()
            ).ifPresent(userRole::setRole);
        });

        return userRepository.save(user);
    }

    public Iterable<User> getAllUsers(
            int page,
            int size,
            String sortBy,
            String direction
    ) {
        return userRepository.findAll(
                PageRequest.of(
                        page,
                        size,
                        Sort.by(
                                Sort.Direction.fromString(direction),
                                sortBy
                        )
                )
        );
    }

    public Iterable<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Iterable<Status> getAllStatuses() {
        return statusRepository.findAll();
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
        initWithTestUsers();
    }

    public void initWithTestUsers() {
//        for (int i = 0; i < 35; i++) {
//            log.info(StatusEnum.values()[i % StatusEnum.values().length].name());
//            log.info(RoleEnum.values()[i % RoleEnum.values().length].name());


//            User user = new User(
//                    "User Name" + i,
//                    "email_" + i + "@domain.com",
//                    statusRepository.findByName(
//                            StatusEnum.values()[i % StatusEnum.values().length]
//                    ).get().getName()
//            );
//
//            user.addRole(new Role(RoleEnum.values()[
//                    i % RoleEnum.values().length
//                    ]));
//
//            this.saveUser(user);
//        }
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
            if (statusRepository.findByName(statusName).isEmpty()) {
                this.statusRepository.save(
                        new Status(statusName)
                );
            }
        });
    }
}
