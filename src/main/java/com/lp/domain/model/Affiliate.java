package com.lp.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "affiliate", catalog = "testdb")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Affiliate extends AbstractEntity {
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "affiliate_id")
    @JsonIgnore
    @ToString.Exclude
    Customer affiliate;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "referral_id")
    @JsonIgnore
    @ToString.Exclude
    Customer referral;

    @ManyToOne(
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "affiliated_by")
    @ToString.Exclude
    User affiliatedBy;
}
