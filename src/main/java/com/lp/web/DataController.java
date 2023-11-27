package com.lp.web;

import com.lp.domain.model.SocialProviderEnum;
import com.lp.domain.model.Status;
import com.lp.domain.service.UserService;
import com.lp.web.dto.ResponseRoleDto;
import com.lp.web.dto.mappers.DataDtoMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/data")
public class DataController {

    private final UserService userService;

    private final DataDtoMapper dataDtoMapper;

    public DataController(
            UserService userService,
            DataDtoMapper dataDtoMapper
    ) {
        this.userService = userService;
        this.dataDtoMapper = dataDtoMapper;
    }

    @GetMapping("/roles")
    Collection<ResponseRoleDto> getUserRolesResponse() {
        return userService.getAllRoles().stream().map(dataDtoMapper::mapRoleToDto).toList();
    }

    @GetMapping("/statuses")
    Collection<Status> getUserStatusesResponse() {
        return userService.getAllStatuses();
    }

    @GetMapping("/providers")
    Iterable<SocialProviderEnum> getUserLoginProvidersResponse() {
        return userService.getAllProviders();
    }
}
