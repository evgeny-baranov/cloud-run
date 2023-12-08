package com.lp.web.dto;

import com.lp.domain.model.RoleEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Collection;

@Data
@SuperBuilder
@NoArgsConstructor
public class ResponseRoleDto {
    RoleEnum name;
    Collection<String> privileges;
}
