package com.lp.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Privilege extends AbstractEntity {
    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;
}
