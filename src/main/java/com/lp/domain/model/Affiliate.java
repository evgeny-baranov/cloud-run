package com.lp.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "affiliate", catalog = "testdb")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Affiliate extends AbstractEntity {
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "affiliate_id")
    @JsonIgnore
    Customer affiliate;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "refferal_id")
    @JsonIgnore
    Customer refferal;

    @ManyToOne(
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "affiliated_by")
    User affiliatedBy;
}
