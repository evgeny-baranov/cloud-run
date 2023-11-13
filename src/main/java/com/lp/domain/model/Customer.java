package com.lp.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer extends AbstractUuidEntity {
    private String name;

    @ManyToOne(optional = true)
    @JoinColumn(name = "parent_id")
    private Customer parent;
}
