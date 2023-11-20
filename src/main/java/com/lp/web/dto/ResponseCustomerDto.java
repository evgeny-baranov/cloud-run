package com.lp.web.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ResponseCustomerDto extends AbstractResponseDto {
    private String name;
}
