package com.lp.domain.repository;

import com.lp.domain.model.OauthProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthProviderRepository extends JpaRepository<OauthProvider, Long> {
}
