package com.lp.web.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class PageDto<T> {
    private int number;
    private int totalPages;
    private long totalElements;
    private SortDto sort;
    private Collection<T> data = new ArrayList<>();
}
