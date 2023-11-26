package com.lp.web.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class ResponseCampaignDto extends AbstractResponseDto {
    String name;
}
