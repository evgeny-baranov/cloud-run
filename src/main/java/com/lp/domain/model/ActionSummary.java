package com.lp.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "campaign", catalog = "testdb")
@Data
public class ActionSummary extends AbstractEntity {
    @ManyToOne(optional = true)
    private User user;

    @ManyToOne
    private Campaign campaign;

    @ManyToOne
    private ActionType actionType;

    private Long count;
}