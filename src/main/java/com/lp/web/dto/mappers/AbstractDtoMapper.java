package com.lp.web.dto.mappers;

import com.lp.web.dto.PageDto;
import com.lp.web.dto.SortDto;
import com.lp.web.dto.SortOrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public class AbstractDtoMapper {
    protected SortDto mapSortToDto(Sort sort) {
        SortDto dto = new SortDto();
        sort.forEach(order -> dto.getOrders().add(
                new SortOrderDto(
                        order.getProperty(),
                        order.getDirection()
                )
        ));

        return dto;
    }

    protected <T, U> void mapPageData(Page<T> page, PageDto<U> dto) {
        dto.setTotalPages(page.getTotalPages());
        dto.setNumber(page.getNumber() + 1);
        dto.setTotalElements(page.getTotalElements());
        dto.setSort(mapSortToDto(page.getSort()));
    }
}
