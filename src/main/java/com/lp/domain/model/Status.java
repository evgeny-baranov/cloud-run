package com.lp.domain.model;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "status", catalog = "testdb")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Status extends AbstractEntity {
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private StatusEnum name;
}
