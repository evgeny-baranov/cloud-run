package com.lp.secrets;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

@Service
public interface CredentialsService {
    Credentials getGithubCredentials() throws Exception;
    Credentials getGoogleCredentials() throws Exception;
    Credentials buildFromJson(String json) throws JsonProcessingException;
}
