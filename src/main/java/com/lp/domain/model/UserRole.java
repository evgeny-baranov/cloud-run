package com.lp.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(
        name = "user_role",
        catalog = "testdb",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {
                        "user_id",
                        "role_id"
                })
        }
)
@Getter
@Setter
public class UserRole extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "role_id")
    private Role role;
}
