package com.lp.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
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
