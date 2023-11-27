package com.lp.domain.service;

import com.lp.domain.model.*;
import com.lp.domain.repository.ActionRepository;
import com.lp.domain.repository.ActionTypeRepository;
import com.lp.domain.repository.CampaignRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class CampaignServiceImpl implements CampaignService {

    private final ActionRepository actionRepository;

    private final ActionTypeRepository actionTypeRepository;

    private final GenericCache<ActionType, ActionTypeEnum, ActionTypeRepository> actionCache;

    private final CampaignRepository campaignRepository;

    private final CustomerService customerService;

    public CampaignServiceImpl(
            CampaignRepository campaignRepository,
            ActionRepository actionRepository,
            ActionTypeRepository actionTypeRepository,
            GenericCache<ActionType, ActionTypeEnum, ActionTypeRepository> actionCache,
            CustomerService customerService
    ) {
        this.campaignRepository = campaignRepository;
        this.actionRepository = actionRepository;
        this.actionTypeRepository = actionTypeRepository;
        this.actionCache = actionCache;
        this.customerService = customerService;
    }

    @PostConstruct
    public void init() {
        initActionTypes();
    }

    public void initActionTypes() {
        Arrays.stream(ActionTypeEnum.values()).forEach(actionTypeName -> {
            if (actionTypeRepository.findByName(actionTypeName).isEmpty()) {
                this.actionTypeRepository.save(
                        new ActionType(actionTypeName)
                );
            }
        });

        actionCache.refresh();

        for (int i = 0; i < 3; i++) {
            Campaign c = new Campaign(
                    "Campaign #" + i,
                    customerService.getOwner()
            );
            c = saveCampaign(c);
            for (int i2 = 0; i2 < 3; i2++) {
                Action a = new Action();
                a.setName("Action #" + i + "." + i2);
                a.setType(ActionTypeEnum.USER_INVITE);
                c.addAction(a);
                saveAction(a);
            }

        }
    }

    @Override
    public Action saveAction(Action action) {
        ActionType type = actionCache.get(
                action.getType().getName()
        ).orElseGet(() -> {
            ActionType actionType = actionTypeRepository.save(
                    new ActionType(action.getType().getName()
                    )
            );
            actionCache.add(actionType);
            return actionType;
        });

        action.setType(type);
        return actionRepository.save(action);
    }

    @Override
    public Collection<Action> getAllActions() {
        return actionRepository.findAll();
    }

    @Transactional
    public Campaign saveCampaign(Campaign campaign) {
        campaign.getActions().forEach(this::saveAction);

        return campaignRepository.save(campaign);
    }

    public Collection<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }

    public Optional<Campaign> findByUuid(UUID uuid) {
        return campaignRepository.findByUuid(uuid);
    }
}
