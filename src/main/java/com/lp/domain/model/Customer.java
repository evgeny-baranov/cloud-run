package com.lp.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "customer", catalog = "testdb")
public class Customer extends AbstractEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
