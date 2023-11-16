package com.lp.web.dto;

import com.lp.domain.model.Customer;
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

    protected void mapPageData(Page<Customer> page, PageDto<ResponseCustomerDto> dto) {
        dto.setTotalPages(page.getTotalPages());
        dto.setNumber(page.getNumber() + 1);
        dto.setTotalElements(page.getTotalElements());
        dto.setSort(mapSortToDto(page.getSort()));
    }
}
