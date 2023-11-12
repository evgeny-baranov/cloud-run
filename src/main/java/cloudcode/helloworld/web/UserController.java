package cloudcode.helloworld.web;

import cloudcode.helloworld.web.dto.DtoMapper;
import cloudcode.helloworld.web.dto.PageDto;
import cloudcode.helloworld.web.dto.UserDto;
import com.lp.domain.model.Role;
import com.lp.domain.model.SortDirectionEnum;
import com.lp.domain.model.Status;
import com.lp.domain.model.User;
import com.lp.domain.service.UserService;
import lombok.extern.java.Log;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
            @RequestParam(defaultValue = "ASC") SortDirectionEnum sortDirection) {
        PageDto<UserDto> dto = new PageDto<>();
        dto.setPageNum(page);
        dto.setSortBy(sortBy);
        dto.setSortDirection(sortDirection);

        userService.getAllUsers(
                page-1,
                10,
                sortBy,
                sortDirection.name()
        ).forEach(user -> dto.getData().add(
                this.dtoMapper.mapUserToDto(user)
        ));

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
        return dtoMapper.mapUserToDto(
                userService.saveUser(
                        dtoMapper.mapDtoToUser(
                                new User(),
                                userDto
                        )
                )
        );
    }

    @PutMapping("{id}")
    UserDto putUserResponse(
            @PathVariable("id") Long id,
            @RequestBody UserDto userDto
    ) {
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User [" + id + "] not found");
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
