package com.lp.web.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SortDto {
    private List<SortOrderDto> orders = new ArrayList<>();
}
