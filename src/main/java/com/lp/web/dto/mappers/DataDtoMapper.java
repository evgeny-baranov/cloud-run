package com.lp.web.dto.mappers;

import com.lp.domain.model.Privilege;
import com.lp.domain.model.Role;
import com.lp.web.dto.ResponseRoleDto;
import org.springframework.stereotype.Component;

@Component
public class DataDtoMapper {
    public ResponseRoleDto mapRoleToDto(Role role) {
        return ResponseRoleDto.builder()
                .name(role.getName())
                .privileges(role.getPrivileges().stream().map(Privilege::getName).toList())
                .build();
    }


}
