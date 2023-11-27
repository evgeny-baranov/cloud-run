package com.lp.domain.service;

import com.lp.domain.model.Action;
import com.lp.domain.model.Campaign;
import com.lp.domain.model.Customer;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public interface CampaignService {

    Action saveAction(Action action);

    Collection<Action> getAllActions();

    Campaign saveCampaign(Campaign campaign);

    Collection<Campaign> getAllCampaigns();

    Optional<Campaign> findByUuid(UUID uuid);

    Optional<Campaign> findByCustomerAndUuid(
            Customer customer,
            UUID uuid);
}
