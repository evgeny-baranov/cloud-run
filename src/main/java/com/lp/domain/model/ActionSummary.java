package com.lp.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActionSummary extends AbstractEntity {
    @ManyToOne(optional = true)
    private User user;

    @ManyToOne
    private Campaign campaign;

    @ManyToOne
    private ActionType actionType;

    private Long count;
}