package com.lp.web.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class ResponseCustomerDto extends AbstractResponseDto {
    private String name;
}
