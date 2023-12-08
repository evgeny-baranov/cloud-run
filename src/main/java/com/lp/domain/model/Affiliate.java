package com.lp.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Affiliate extends AbstractEntity {
    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIgnore
    @ToString.Exclude
    Customer affiliate;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIgnore
    @ToString.Exclude
    Customer referral;

    @ManyToOne(
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER
    )
    @ToString.Exclude
    User affiliatedBy;
}
