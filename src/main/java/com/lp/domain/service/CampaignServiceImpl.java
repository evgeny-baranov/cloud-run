package com.lp.domain.service;

import com.lp.domain.model.*;
import com.lp.domain.repository.ActionRepository;
import com.lp.domain.repository.ActionTypeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;

@Service
public class CampaignServiceImpl implements CampaignService {

    private final ActionRepository actionRepository;
    private final ActionTypeRepository actionTypeRepository;
    private final GenericCache<ActionType, ActionTypeEnum, ActionTypeRepository> actionCache;

    public CampaignServiceImpl(
            ActionRepository actionRepository,
            ActionTypeRepository actionTypeRepository,
            GenericCache<ActionType, ActionTypeEnum, ActionTypeRepository> actionCache
    ) {
        this.actionRepository = actionRepository;
        this.actionTypeRepository = actionTypeRepository;
        this.actionCache = actionCache;
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
}
