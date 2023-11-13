package com.lp.domain.service;

import com.lp.domain.model.Role;
import com.lp.domain.model.RoleEnum;
import com.lp.domain.model.Status;
import com.lp.domain.model.StatusEnum;
import com.lp.domain.repository.RoleRepository;
import com.lp.domain.repository.StatusRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {
    @Bean
    public GenericCache<Status, StatusEnum, StatusRepository> statusCache(StatusRepository repository) {
        return new GenericCache<>(repository, Status::getName);
    }

    @Bean
    public GenericCache<Role, RoleEnum, RoleRepository> roleCache(RoleRepository repository) {
        return new GenericCache<>(repository, Role::getName);
    }
}
