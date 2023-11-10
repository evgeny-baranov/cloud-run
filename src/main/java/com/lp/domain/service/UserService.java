package com.lp.domain.service;

import com.lp.domain.model.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

    public User saveUser(User user);

    Iterable<User> getAllUsers();

    public Iterable<Role> getAllRoles();

    public Iterable<Status> getAllStatuses();

    public void initRoles();

    public void initUserStatuses();

    public Optional<User> findByEmail(String email);

    public Optional<User> findById(Long id);
}