package cloudcode.helloworld.web;

import cloudcode.helloworld.web.dto.DtoMapper;
import cloudcode.helloworld.web.dto.PageDto;
import cloudcode.helloworld.web.dto.UserDto;
import com.lp.domain.model.Role;
import com.lp.domain.model.Status;
import com.lp.domain.model.User;
import com.lp.domain.service.UserService;
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
    PageDto<UserDto> getUserListResponse(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection) {

        return dtoMapper.mapPageToDto(
                userService.getAllUsers(
                        page,
                        10,
                        sortBy,
                        sortDirection
                )
        );
    }

    @GetMapping("/{uuid}")
    UserDto getUserResponse(
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
    UserDto postUserResponse(@RequestBody UserDto userDto) {
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
    UserDto putUserResponse(
            @PathVariable("uuid") UUID uuid,
            @RequestBody UserDto userDto
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
