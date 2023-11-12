package cloudcode.helloworld.web.dto;

import com.lp.domain.model.*;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;

@Component
public class DtoMapper {
    public User mapDtoToUser(User user, UserDto dto) {
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setStatus(new Status(dto.getStatus()));

        dto.getRoles().forEach(roleEnum -> {
            user.addRole(new Role(roleEnum));
        });

        return user;
    }

    public Collection<RoleEnum> mapRoleSetToDto(Collection<UserRole> roles) {
        Collection<RoleEnum> set = new HashSet<>();

        roles.forEach(userRole -> set.add(userRole.getRole().getName()));

        return set;
    }

    public StatusEnum mapUserStatusToDto(Status userStatus) {
        return userStatus.getName();
    }

    public UserDto mapUserToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .roles(mapRoleSetToDto(user.getRoles()))
                .status(mapUserStatusToDto(user.getStatus()))
                .build();
    }
}
