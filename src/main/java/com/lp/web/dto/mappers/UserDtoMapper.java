package com.lp.web.dto.mappers;

import com.lp.domain.model.*;
import com.lp.web.dto.PageDto;
import com.lp.web.dto.RequestCreateUserDto;
import com.lp.web.dto.RequestUpdateUserDto;
import com.lp.web.dto.ResponseUserDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Component
public class UserDtoMapper extends AbstractDtoMapper {
    public User mapCreateDtoToUser(User user, RequestCreateUserDto dto) {
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        user.setStatus(new Status(dto.getStatus()));

        user.getRoles().removeIf(userRole -> !dto.getRoles().contains(userRole.getRole().getName()));
        dto.getRoles().forEach(user::addRole);

        return user;
    }

    public User mapUpdateDtoToUser(User user, RequestUpdateUserDto dto) {
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
            user.getRoles().removeIf(userRole -> !dto.getRoles().contains(userRole.getRole().getName()));
            dto.getRoles().forEach(user::addRole);
        }

        return user;
    }

    private Set<RoleEnum> mapRoleSetToDto(Collection<UserRole> roles) {
        Set<RoleEnum> set = new HashSet<>();

        roles.forEach(userRole -> set.add(userRole.getRole().getName()));

        return set;
    }

    private StatusEnum mapUserStatusToDto(Status userStatus) {
        return userStatus.getName();
    }

    public ResponseUserDto mapUserToDto(User user) {
        return ResponseUserDto.builder()
                .uuid(user.getUuid())
                .name(user.getName())
                .email(user.getEmail())
                .roles(mapRoleSetToDto(user.getRoles()))
                .status(mapUserStatusToDto(user.getStatus()))
                .build();
    }


    public PageDto<ResponseUserDto> mapPageToDto(Page<User> page) {
        PageDto<ResponseUserDto> dto = new PageDto<>();

        dto.setTotalPages(page.getTotalPages());
        dto.setNumber(page.getNumber() + 1);
        dto.setTotalElements(page.getTotalElements());

        dto.setSort(mapSortToDto(page.getSort()));

        page.forEach(user -> dto.getData().add(mapUserToDto(user)));

        return dto;
    }
}
