package com.lp.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Status extends AbstractEntity {
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private StatusEnum name;
}
