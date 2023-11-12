package com.lp.domain.service;

import com.lp.domain.model.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

    User saveUser(User user);

    Iterable<User> getAllUsers(
            int page,
            int size,
            String sortBy,
            String direction
    );

    Iterable<Role> getAllRoles();

    Iterable<Status> getAllStatuses();

    void initRoles();

    void initUserStatuses();

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);
}