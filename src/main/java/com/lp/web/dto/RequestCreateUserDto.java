package com.lp.web.dto;

import com.lp.domain.model.RoleEnum;
import com.lp.domain.model.StatusEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class RequestCreateUserDto {

    @NotEmpty(message = "NAME_NOT_EMPTY")
    @Size(min = 8)
    private String name;

    @NotEmpty(message = "EMAIL_NOT_EMPTY")
    @Email(message = "INVALID_EMAIL")
    private String email;

    @NotEmpty
    private Set<RoleEnum> roles;

    private StatusEnum status;
}
