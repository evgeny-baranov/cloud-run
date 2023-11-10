package com.lp.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "status", catalog = "testdb")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Status extends AbstractEntity {
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private StatusEnum name = StatusEnum.STATUS_PENDING;
}
