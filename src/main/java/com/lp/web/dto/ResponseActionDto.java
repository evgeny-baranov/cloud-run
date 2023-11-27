package com.lp.web.dto;

import com.lp.domain.model.ActionTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class ResponseActionDto extends AbstractResponseDto {
    String name;
    ActionTypeEnum type;
    Map<String, Object> settings;
}
