package com.lp.domain.model;

import lombok.Getter;

@Getter
public enum SocialProviderEnum {
    FACEBOOK("facebook"),
    TWITTER("twitter"),
    LINKEDIN("linkedin"),
    GOOGLE("google"),
    GITHUB("github"),
    LOCAL("local");

    private final String providerType;

    SocialProviderEnum(final String providerType) {
        this.providerType = providerType;
    }
}
