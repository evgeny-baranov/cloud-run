package com.lp.web.dto.mappers;

import com.lp.domain.model.Action;
import com.lp.domain.model.Campaign;
import com.lp.domain.model.Customer;
import com.lp.web.dto.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CustomerDtoMapper extends AbstractDtoMapper {

    public Customer mapUpdateDtoToCustomer(
            Customer customer,
            RequestUpdateCustomerDto dto
    ) {
        customer.setName(dto.getName());

        return customer;
    }

    public Customer mapCreateDtoToCustomer(
            Customer customer,
            RequestCreateCustomerDto dto,
            Customer affiliate
    ) {
        customer.setName(dto.getName());
        customer.setAffiliate(affiliate);

        return customer;
    }

    public ResponseCustomerDto mapCustomerToDto(Customer customer) {
        return ResponseCustomerDto.builder()
                .uuid(customer.getUuid())
                .name(customer.getName())
                .build();
    }

    public PageDto<ResponseCustomerDto> mapCustomerPageToDto(Page<Customer> page) {
        PageDto<ResponseCustomerDto> dto = new PageDto<>();

        mapPageData(page, dto);

        page.forEach(customer -> dto.getData().add(mapCustomerToDto(customer)));

        return dto;
    }

    public PageDto<ResponseCampaignDto> mapCampaignPageToDto(Page<Campaign> page) {
        PageDto<ResponseCampaignDto> dto = new PageDto<>();

        mapPageData(page, dto);

        page.forEach(campaign -> dto.getData().add(mapCampaignToDto(campaign)));

        return dto;
    }

    public ResponseCampaignDto mapCampaignToDto(Campaign campaign) {
        return ResponseCampaignDto.builder()
                .uuid(campaign.getUuid())
                .name(campaign.getName())
                .build();
    }

    public Campaign mapCreateDtoToCampaign(
            Campaign campaign,
            RequestCreateCampaignDto dto
    ) {
        campaign.setName(dto.getName());

        return campaign;
    }

    public Campaign mapUpdateDtoToCampaign(
            Campaign campaign,
            @Valid RequestUpdateCampaignDto dto
    ) {
        campaign.setName(dto.getName());

        return campaign;
    }

    public ResponseActionDto mapActionToDto(Action action) {
        Map<String, Object> settings = action.getSettings().getMap();
        action.getType().getSettings().forEach(key -> {
            settings.putIfAbsent(key, null);
        });

        return ResponseActionDto.builder()
                .uuid(action.getUuid())
                .name(action.getName())
                .type(action.getType().getName())
                .settings(action.getSettings().getMap())
                .build();
    }
}
