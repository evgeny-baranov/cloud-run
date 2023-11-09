package com.lp.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "oauth_provider", catalog = "testdb")
public class OauthProvider extends AbstractEntity {
    private String name;

    public OauthProvider() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OauthProvider(String name) {
        this.name = name;
    }
}
