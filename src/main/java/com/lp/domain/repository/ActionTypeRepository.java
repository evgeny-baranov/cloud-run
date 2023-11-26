package com.lp.domain.repository;

import com.lp.domain.model.ActionType;
import com.lp.domain.model.ActionTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActionTypeRepository extends JpaRepository<ActionType, Long> {
    Optional<ActionType> findByName(ActionTypeEnum name);
}
