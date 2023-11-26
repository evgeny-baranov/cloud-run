package com.lp.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestUpdateCampaignDto {
    @NotBlank(message = "CUSTOMER_NAME_NOT_BLANK")
    private String name;
}
