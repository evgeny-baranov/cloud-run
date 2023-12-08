package com.lp.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(
        callSuper = true,
        exclude = {"affiliate"}
)
@Entity
@Getter
@Setter
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
