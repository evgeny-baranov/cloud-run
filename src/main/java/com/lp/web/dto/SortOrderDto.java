package com.lp.web.dto;

import lombok.*;
import org.springframework.data.domain.Sort;


@Data
@AllArgsConstructor
public class SortOrderDto {
    private String property;
    private Sort.Direction direction;
}

