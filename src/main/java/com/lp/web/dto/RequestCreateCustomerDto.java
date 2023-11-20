package com.lp.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestCreateCustomerDto {

    @NotBlank(message = "CUSTOMER_NAME_NOT_BLANK")
    private String name;
}
