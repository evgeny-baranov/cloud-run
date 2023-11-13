package cloudcode.helloworld.web.dto;

import com.lp.domain.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.HashSet;

@Component
public class DtoMapper {
    public User mapDtoToUser(User user, UserDto dto) {
        user.setName(
                dto.getName() == null
                        ? user.getName()
                        : dto.getName()
        );
        user.setEmail(
                dto.getEmail() == null
                        ? user.getEmail()
                        : dto.getEmail()
        );

        user.setStatus(
                dto.getStatus() == null
                        ? user.getStatus()
                        : new Status(dto.getStatus())
        );

        if (dto.getRoles() != null) {
            user.getRoles().clear();
            dto.getRoles().forEach(user::addRole);
        }

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
                .uuid(user.getUuid())
                .name(user.getName())
                .email(user.getEmail())
                .roles(mapRoleSetToDto(user.getRoles()))
                .status(mapUserStatusToDto(user.getStatus()))
                .build();
    }

    public PageDto<UserDto> mapPageToDto(Page<User> page) {
        PageDto<UserDto> dto = new PageDto<>();

        dto.setTotalPages(page.getTotalPages());
        dto.setNumber(page.getNumber() + 1);
        dto.setTotalElements(page.getTotalElements());

        dto.setSort(mapSortToDto(page.getSort()));

        page.forEach(user -> dto.getData().add(mapUserToDto(user)));

        return dto;
    }

    public SortDto mapSortToDto(Sort sort) {
        SortDto dto = new SortDto();
        sort.forEach(order -> dto.getOrders().add(
                new SortOrderDto(
                        order.getProperty(),
                        order.getDirection()
                )
        ));

        return dto;
    }
}
