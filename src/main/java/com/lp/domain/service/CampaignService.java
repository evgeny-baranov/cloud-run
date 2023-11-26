package com.lp.domain.service;

import com.lp.domain.model.Action;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface CampaignService {
    Action saveAction(Action action);

    Collection<Action> getAllActions();
}
