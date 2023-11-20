package com.lp.web.dto;

import com.lp.domain.model.RoleEnum;
import com.lp.domain.model.StatusEnum;
import com.lp.web.dto.validator.NullOrEmail;
import com.lp.web.dto.validator.NullOrNotBlank;
import lombok.Data;

import java.util.Set;

@Data
public class RequestUpdateUserDto {

    @NullOrNotBlank(message = "NAME_NULL_OR_NOT_BLANK")
    private String name;

    @NullOrEmail(message = "EMAIL_NULL_OR_VALID")
    private String email;

    private Set<RoleEnum> roles;

    private StatusEnum status;
}
