package com.lp.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customer", catalog = "testdb")
@Getter
@Setter
public class Customer extends AbstractEntity {

    private String name;
}
