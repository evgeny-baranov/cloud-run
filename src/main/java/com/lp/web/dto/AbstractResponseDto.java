package com.lp.web.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@SuperBuilder
public class AbstractResponseDto {
    protected UUID uuid;
}
