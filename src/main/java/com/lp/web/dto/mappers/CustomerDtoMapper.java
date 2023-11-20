package com.lp.web.dto.mappers;

import com.lp.domain.model.Customer;
import com.lp.web.dto.PageDto;
import com.lp.web.dto.ResponseCustomerDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoMapper extends AbstractDtoMapper {

    public ResponseCustomerDto mapCustomerToDto(Customer customer) {
        return ResponseCustomerDto.builder()
                .uuid(customer.getUuid())
                .name(customer.getName())
                .build();
    }

    public PageDto<ResponseCustomerDto> mapPageToDto(Page<Customer> page) {
        PageDto<ResponseCustomerDto> dto = new PageDto<>();

        mapPageData(page, dto);

        page.forEach(user -> dto.getData().add(mapCustomerToDto(user)));

        return dto;
    }
}
