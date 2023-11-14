package com.lp.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SortOrderDto {
    private String property;
    private Sort.Direction direction;
}

