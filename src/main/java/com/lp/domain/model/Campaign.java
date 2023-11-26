package com.lp.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "campaign", catalog = "testdb")
@Getter
@Setter
@NoArgsConstructor
public class Campaign extends AbstractUuidEntity {
    String name;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    @ToString.Exclude
    Customer customer;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "campaign",
            fetch = FetchType.EAGER
    )
    private Set<Action> actions = new HashSet<>();

    public Campaign(String name, Customer customer) {
        this.name = name;
        this.customer = customer;
    }

    public void addAction(Action action) {
        action.setCampaign(this);
        this.actions.add(action);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), customer);
    }
}
