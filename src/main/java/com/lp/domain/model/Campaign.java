package com.lp.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "campaign", catalog = "testdb")
@Getter
@Setter
public class Campaign extends AbstractUuidEntity {
    String name;

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    Customer customer;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "campaign",
            fetch = FetchType.EAGER
    )
    @ToString.Exclude
    private Set<Action> actions = new HashSet<>();

    public Campaign() {
    }

    public Campaign(String name, Customer customer) {
        this.name = name;
        this.customer = customer;
    }
}
