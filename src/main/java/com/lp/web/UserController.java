package com.lp.web;

import com.lp.domain.model.Role;
import com.lp.domain.model.SortDirectionEnum;
import com.lp.domain.model.Status;
import com.lp.domain.model.User;
import com.lp.domain.service.UserService;
import com.lp.web.dto.DtoMapper;
import com.lp.web.dto.PageDto;
import com.lp.web.dto.RequestUserDto;
import com.lp.web.dto.ResponseUserDto;
import lombok.extern.java.Log;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@Log
public class UserController {

    final UserService userService;

    final DtoMapper dtoMapper;

    public UserController(UserService userService, DtoMapper dtoMapper) {
        this.userService = userService;
        this.dtoMapper = dtoMapper;
    }

    @GetMapping("/roles")
    Iterable<Role> getUserRolesResponse() {
        return userService.getAllRoles();
    }

    @GetMapping("/statuses")
    Iterable<Status> getUserStatusesResponse() {
        return userService.getAllStatuses();
    }

    @GetMapping("/list")
    PageDto<ResponseUserDto> getUserListResponse(
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "ASC") SortDirectionEnum sortDirection) {

        return dtoMapper.mapPageToDto(
                userService.getAllUsers(
                        pageNumber,
                        pageSize,
                        sortBy,
                        sortDirection.name()
                )
        );
    }

    @GetMapping("/{uuid}")
    ResponseUserDto getUserResponse(
            @PathVariable("uuid") UUID uuid
    ) {
        Optional<User> optionalUser = userService.findById(uuid);

        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User [" + uuid + "] not found");
        }

        return dtoMapper.mapUserToDto(
                optionalUser.get()
        );
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseUserDto postUserResponse(@RequestBody RequestUserDto userDto) {
        return dtoMapper.mapUserToDto(
                userService.saveUser(
                        dtoMapper.mapDtoToUser(
                                new User(),
                                userDto
                        )
                )
        );
    }

    @PutMapping("{uuid}")
    ResponseUserDto putUserResponse(
            @PathVariable("uuid") UUID uuid,
            @RequestBody RequestUserDto userDto
    ) {
        Optional<User> optionalUser = userService.findById(uuid);

        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User [" + uuid + "] not found");
        }

        return dtoMapper.mapUserToDto(
                userService.saveUser(
                        dtoMapper.mapDtoToUser(
                                optionalUser.get(),
                                userDto
                        )
                )
        );
    }
}
