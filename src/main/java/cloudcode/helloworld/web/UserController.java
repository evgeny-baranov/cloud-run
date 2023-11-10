package cloudcode.helloworld.web;

import cloudcode.helloworld.web.dto.DtoMapper;
import cloudcode.helloworld.web.dto.UserDto;
import com.lp.domain.model.Role;
import com.lp.domain.model.User;
import com.lp.domain.model.Status;
import com.lp.domain.service.UserService;
import lombok.extern.java.Log;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
    Set<UserDto> getUserListResponse() {
        Set<UserDto> dto = new HashSet<>();

        userService.getAllUsers().forEach(user -> {
            dto.add(
                    this.dtoMapper.mapUserToDto(user)
            );
        });

        return dto;
    }

    @GetMapping("/{id}")
    UserDto getUserResponse(
            @PathVariable Long id
    ) {
        Optional<User> optionalUser = userService.findById(id);

        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        return dtoMapper.mapUserToDto(
                optionalUser.get()
        );
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    UserDto postUserResponse(@RequestBody UserDto userDto) {
        log.info(userDto.toString());
        log.info(dtoMapper.mapDtoToUser(userDto).toString());
//        return userDto;
        return dtoMapper.mapUserToDto(
                userService.saveUser(
                        dtoMapper.mapDtoToUser(userDto)
                )
        );
    }

    @PutMapping("{id}")
    UserDto putUserResponse(
            @PathVariable("id") Long id,
            @RequestBody UserDto userDto
    ) {
        log.info(userDto.toString());
        return userDto;
    }
}
