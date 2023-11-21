package com.lp.web.dto;

import com.lp.web.dto.validator.NullOrUUID;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestCreateCustomerDto {

    @NotBlank(message = "CUSTOMER_NAME_NOT_BLANK")
    private String name;

    @NullOrUUID(message = "CUSTOMER_AFFILIATE_NULL_OR_UUID")
    private String affiliate;
}
