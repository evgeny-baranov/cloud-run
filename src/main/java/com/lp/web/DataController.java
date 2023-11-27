package com.lp.web;

import com.lp.domain.model.Role;
import com.lp.domain.model.SocialProviderEnum;
import com.lp.domain.model.Status;
import com.lp.domain.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/data")
public class DataController {

    private final UserService userService;

    public DataController(
            UserService userService
    ) {
        this.userService = userService;
    }

    @GetMapping("/roles")
    Iterable<Role> getUserRolesResponse() {
        return userService.getAllRoles();
    }

    @GetMapping("/statuses")
    Iterable<Status> getUserStatusesResponse() {
        return userService.getAllStatuses();
    }

    @GetMapping("/providers")
    Iterable<SocialProviderEnum> getUserLoginProvidersResponse() {
        return userService.getAllProviders();
    }
}
