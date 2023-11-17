package com.lp.web;

import com.lp.domain.model.Customer;
import com.lp.domain.model.SortDirectionEnum;
import com.lp.domain.service.CustomerService;
import com.lp.web.dto.CustomerDtoMapper;
import com.lp.web.dto.PageDto;
import com.lp.web.dto.ResponseCustomerDto;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/customer")
@Log
public class CustomerController {

    private final CustomerService customerService;

    private final CustomerDtoMapper customerDtoMapper;

    public CustomerController(
            CustomerService customerService,
            CustomerDtoMapper customerDtoMapper
    ) {
        this.customerService = customerService;
        this.customerDtoMapper = customerDtoMapper;
    }

    @GetMapping("/list")
    PageDto<ResponseCustomerDto> getCustomerListResponse(
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "ASC") SortDirectionEnum sortDirection) {

        return customerDtoMapper.mapPageToDto(
                customerService.getPagedCustomers(
                        pageNumber,
                        pageSize,
                        sortBy,
                        sortDirection.name()
                )
        );
    }

    @GetMapping("/{uuid}")
    ResponseCustomerDto getCustomerResponse(
            @PathVariable("uuid") UUID uuid
    ) {
        Optional<Customer> optionalCustomer = customerService.findByUuid(uuid);

        if (optionalCustomer.isEmpty()) {
            throw new IllegalArgumentException("Customer [" + uuid + "] not found");
        }

        return customerDtoMapper.mapCustomerToDto(
                optionalCustomer.get()
        );
    }

    @GetMapping("/{uuid}/referral/list")
    PageDto<ResponseCustomerDto> getCustomerReferralsResponse(
            @PathVariable("uuid") UUID uuid,
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "referral.name") String sortBy,
            @RequestParam(defaultValue = "ASC") SortDirectionEnum sortDirection
    ) {
        Optional<Customer> optionalCustomer = customerService.findByUuid(uuid);

        if (optionalCustomer.isEmpty()) {
            throw new IllegalArgumentException("Customer [" + uuid + "] not found");
        }

        return customerDtoMapper.mapPageToDto(
                customerService.getPagedCustomerReferrals(
                        optionalCustomer.get(),
                        pageNumber,
                        pageSize,
                        sortBy,
                        sortDirection.name()
                )
        );
    }
}
