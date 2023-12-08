package com.lp.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
public class UserRole extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIgnore
    private User user;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Role role;
}
