package com.lp.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_status", catalog = "testdb")
public class UserStatus extends AbstractEntity {
    @Enumerated(EnumType.STRING)
    private UserStatusEnum status = UserStatusEnum.STATUS_PENDING;
}
