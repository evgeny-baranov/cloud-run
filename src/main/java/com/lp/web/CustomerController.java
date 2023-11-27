package com.lp.web;

import com.lp.domain.model.Campaign;
import com.lp.domain.model.Customer;
import com.lp.domain.model.SortDirectionEnum;
import com.lp.domain.service.CampaignService;
import com.lp.domain.service.CustomerService;
import com.lp.web.dto.*;
import com.lp.web.dto.mappers.CustomerDtoMapper;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/api/customer")
@Log
public class CustomerController {

    private final CustomerService customerService;

    private final CustomerDtoMapper customerDtoMapper;

    private final CampaignService campaignService;

    public CustomerController(
            CustomerService customerService,
            CustomerDtoMapper customerDtoMapper,
            CampaignService campaignService
    ) {
        this.customerService = customerService;
        this.customerDtoMapper = customerDtoMapper;
        this.campaignService = campaignService;
    }

    @GetMapping("/")
    PageDto<ResponseCustomerDto> getCustomerListResponse(
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "ASC") SortDirectionEnum sortDirection) {

        return customerDtoMapper.mapCustomerPageToDto(
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
            path = "/{uuid}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseCustomerDto putCustomerResponse(
            @PathVariable("uuid") UUID uuid,
            @Valid @RequestBody RequestUpdateCustomerDto dto
    ) {
        Customer customer = customerService.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Customer [" + uuid + "] not found"));

        return customerDtoMapper.mapCustomerToDto(
                customerService.saveCustomer(
                        customerDtoMapper.mapUpdateDtoToCustomer(
                                customer,
                                dto
                        )
                )
        );
    }

    @GetMapping("/{uuid}")
    ResponseCustomerDto getCustomerResponse(
            @PathVariable("uuid") UUID uuid
    ) {
        Customer customer = customerService.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Customer [" + uuid + "] not found"));

        return customerDtoMapper.mapCustomerToDto(
                customer
        );
    }

    @GetMapping("/{uuid}/referral")
    PageDto<ResponseCustomerDto> getCustomerReferralsResponse(
            @PathVariable("uuid") UUID uuid,
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "referral.name") String sortBy,
            @RequestParam(defaultValue = "ASC") SortDirectionEnum sortDirection
    ) {
        Customer customer = customerService.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Customer [" + uuid + "] not found"));

        return customerDtoMapper.mapCustomerPageToDto(
                customerService.getPagedCustomerReferrals(
                        customer,
                        pageNumber,
                        pageSize,
                        sortBy,
                        sortDirection.name()
                )
        );
    }

    @GetMapping("/{uuid}/campaign")
    PageDto<ResponseCampaignDto> getCustomerCampaignResponse(
            @PathVariable("uuid") UUID uuid,
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "ASC") SortDirectionEnum sortDirection
    ) {
        Customer customer = customerService.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Customer [" + uuid + "] not found"));

        return customerDtoMapper.mapCampaignPageToDto(
                customerService.getPagedCustomerCampaigns(
                        customer,
                        pageNumber,
                        pageSize,
                        sortBy,
                        sortDirection.name()
                )
        );
    }

    @PostMapping("/{uuid}/campaign")
    ResponseCampaignDto postCustomerCampaignResponse(
            @PathVariable("uuid") UUID uuid,
            @Valid @RequestBody RequestCreateCampaignDto dto
    ) {
        Customer customer = customerService.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Customer [" + uuid + "] not found"));

        return customerDtoMapper.mapCampaignToDto(
                campaignService.saveCampaign(
                        customerDtoMapper.mapCreateDtoToCampaign(
                                new Campaign(customer),
                                dto
                        )
                )
        );
    }

    @PutMapping("/{uuid}/campaign/{campaignUuid}")
    ResponseCampaignDto putCustomerCampaignResponse(
            @PathVariable("uuid") UUID uuid,
            @PathVariable("campaignUuid") UUID campaignUuid,
            @Valid @RequestBody RequestUpdateCampaignDto dto
    ) {
        Customer customer = customerService.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Customer [" + uuid + "] not found"));

        Campaign campaign = campaignService.findByUuid(campaignUuid)
                .orElseThrow(() -> new IllegalArgumentException("Campaign [" + campaignUuid + "] not found"));

        return customerDtoMapper.mapCampaignToDto(
                campaignService.saveCampaign(
                        customerDtoMapper.mapUpdateDtoToCampaign(
                                campaign,
                                dto
                        )
                )
        );
    }

    @GetMapping("/{uuid}/campaign/{campaignUuid}/action")
    Collection<ResponseActionDto> putCustomerCampaignActionsListResponse(
            @PathVariable("uuid") UUID uuid,
            @PathVariable("campaignUuid") UUID campaignUuid
    ) {
        Customer customer = customerService.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Customer [" + uuid + "] not found"));

        Campaign campaign = campaignService.findByUuid(campaignUuid)
                .orElseThrow(() -> new IllegalArgumentException("Campaign [" + campaignUuid + "] not found"));

        return campaign.getActions().stream().map(customerDtoMapper::mapActionToDto).toList();
    }
}
