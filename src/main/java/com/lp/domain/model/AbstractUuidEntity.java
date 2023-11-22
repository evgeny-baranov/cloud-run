package com.lp.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Data
class AbstractUuidEntity extends AbstractEntity {

    @Column(
            updatable = false,
            nullable = false,
            unique = true
    )
    protected UUID uuid;

    @PrePersist
    private void assignUuid() {
        setUuid(UUID.randomUUID());
    }
}
