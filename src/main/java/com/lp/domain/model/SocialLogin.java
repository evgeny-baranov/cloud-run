package com.lp.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "social_login", catalog = "testdb")
public class SocialLogin extends AbstractEntity{
    private String socialId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    private OauthProvider provider;
    private String name;
    private String email;
    private String profilePictureUrl;

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OauthProvider getProvider() {
        return provider;
    }

    public void setProvider(OauthProvider provider) {
        this.provider = provider;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }
}
