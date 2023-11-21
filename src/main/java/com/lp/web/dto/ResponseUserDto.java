package com.lp.web.dto;

import com.lp.domain.model.RoleEnum;
import com.lp.domain.model.StatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class ResponseUserDto extends AbstractResponseDto {
    private String name;
    private String email;
    private Set<RoleEnum> roles;
    private StatusEnum status;
}
