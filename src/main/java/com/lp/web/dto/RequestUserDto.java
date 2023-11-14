package com.lp.web.dto;

import com.lp.domain.model.RoleEnum;
import com.lp.domain.model.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RequestUserDto {
    private String name;
    private String email;
    private Set<RoleEnum> roles;
    private StatusEnum status;
}
