package cloudcode.helloworld.web.dto;

import com.lp.domain.model.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DtoMapper {
    public User mapDtoToUser(UserDto dto) {
        User user = new User(
                dto.getName(),
                dto.getEmail(),
                dto.getStatus()
        );

        dto.getRoles().forEach(roleEnum -> {
            Role role = new Role(roleEnum);
            user.addRole(role);
        });

        return user;
    }

    public Set<RoleEnum> mapRoleSetToDto(Set<UserRole> roles) {
        Set<RoleEnum> set = new HashSet<RoleEnum>();

        roles.forEach(userRole -> {
            set.add(userRole.getRole().getName());
        });

        return set;
    }

    public StatusEnum mapUserStatusToDto(Status userStatus) {
        return userStatus.getName();
    }

    public UserDto mapUserToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                mapRoleSetToDto(user.getRoles()),
                mapUserStatusToDto(user.getStatus())
        );
    }
}
