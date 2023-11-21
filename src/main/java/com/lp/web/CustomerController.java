package com.lp.web;

import com.lp.domain.model.Customer;
import com.lp.domain.model.SortDirectionEnum;
import com.lp.domain.service.CustomerService;
import com.lp.web.dto.RequestCreateCustomerDto;
import com.lp.web.dto.RequestUpdateCustomerDto;
import com.lp.web.dto.mappers.CustomerDtoMapper;
import com.lp.web.dto.PageDto;
import com.lp.web.dto.ResponseCustomerDto;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import org.springframework.http.MediaType;
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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseCustomerDto getCustomerResponse(
            @Valid @RequestBody RequestCreateCustomerDto dto
    ) {
        return customerDtoMapper.mapCustomerToDto(
                customerService.saveCustomer(
                        customerDtoMapper.mapCreateDtoToCustomer(
                                new Customer(),
                                dto,
                                dto.getAffiliate() == null
                                        ? customerService.getOwner()
                                        : customerService.findByUuid(UUID.fromString(dto.getAffiliate()))
                                        .orElseGet(customerService::getOwner)
                        )
                )
        );
    }

    @PutMapping(
            path = "{uuid}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseCustomerDto putCustomerResponse(
            @PathVariable("uuid") UUID uuid,
            @Valid @RequestBody RequestUpdateCustomerDto dto
    ) {
        Optional<Customer> optionalCustomer = customerService.findByUuid(uuid);

        if (optionalCustomer.isEmpty()) {
            throw new IllegalArgumentException("Customer [" + uuid + "] not found");
        }

        return customerDtoMapper.mapCustomerToDto(
                customerService.saveCustomer(
                        customerDtoMapper.mapUpdateDtoToCustomer(
                                optionalCustomer.get(),
                                dto
                        )
                )
        );
    }

    @GetMapping("{uuid}")
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
