package com.lp.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer extends AbstractUuidEntity {
    private String name;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "referral",
            fetch = FetchType.EAGER
    )
    @JsonIgnore
    private Set<Affiliate> affiliate = new HashSet<>();

    public void setAffiliate(Customer affiliate) {
        this.affiliate.add(new Affiliate(
                affiliate,
                this,
                null
        ));
    }
}
