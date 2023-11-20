package com.lp.web.dto;

import com.lp.domain.model.RoleEnum;
import com.lp.domain.model.StatusEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Set;

@Data
public class RequestCreateUserDto {

    @NotBlank(message = "NAME_NOT_BLANK")
    private String name;

    @NotBlank(message = "EMAIL_NOT_BLANK")
    @Email(message = "INVALID_EMAIL")
    private String email;

    @NotEmpty
    private Set<RoleEnum> roles;

    private StatusEnum status;
}
