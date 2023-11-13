package com.lp.domain.service;

import com.lp.domain.model.Role;
import com.lp.domain.model.Status;
import com.lp.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public interface UserService {

    User saveUser(User user);

    Page<User> getAllUsers(
            int page,
            int size,
            String sortBy,
            String direction
    );

    Iterable<Role> getAllRoles();

    Iterable<Status> getAllStatuses();

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    Optional<User> findById(UUID uuid);

    void init();
}