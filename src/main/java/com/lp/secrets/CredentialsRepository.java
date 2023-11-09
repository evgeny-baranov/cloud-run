package com.lp.secrets;

import org.springframework.stereotype.Component;

@Component
public interface CredentialsRepository {

    String getSecretData(String secretName) throws Exception;
}
