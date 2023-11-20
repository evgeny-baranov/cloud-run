package com.lp.web.dto;

import com.lp.domain.model.RoleEnum;
import com.lp.domain.model.StatusEnum;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Collection;
import java.util.HashSet;

@Data
@SuperBuilder
public class ResponseUserDto extends AbstractResponseDto {
    private String name;
    private String email;
    private Collection<RoleEnum> roles = new HashSet<>();
    private StatusEnum status;
}
