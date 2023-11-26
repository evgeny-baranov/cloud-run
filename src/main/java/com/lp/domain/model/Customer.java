package com.lp.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(
        callSuper = true,
        exclude = {"affiliate"}
)
@Entity
@Getter
@Setter
@Table(name = "customer", catalog = "testdb")
public class Customer extends AbstractUuidEntity {

    @NotBlank
    private String name;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "referral",
            fetch = FetchType.EAGER
    )
    @JsonIgnore
    @ToString.Exclude
    private Set<Affiliate> affiliate = new HashSet<>();

    public void setAffiliate(Customer affiliate) {
        this.affiliate.add(new Affiliate(
                affiliate,
                this,
                null
        ));
    }
}
