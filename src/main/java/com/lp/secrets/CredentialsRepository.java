package com.lp.secrets;

import org.springframework.stereotype.Repository;

@Repository
public interface CredentialsRepository {
    String getSecretData(String secretName) throws Exception;
}
