package com.lp.secrets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CredentialsServiceImpl implements CredentialsService {
    private final CredentialsRepository credentialsRepository;

    private final ObjectMapper objectMapper;

    public CredentialsServiceImpl(CredentialsRepository credentialsRepository, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.credentialsRepository = credentialsRepository;
    }

    public Credentials buildFromJson(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, Credentials.class);
    }

    public Credentials getGithubCredentials() throws Exception {
        return this.buildFromJson(
                this.credentialsRepository.getSecretData("github-credentials")
        );
    }

    public Credentials getGoogleCredentials() throws Exception {
        return this.buildFromJson(
                this.credentialsRepository.getSecretData("google-credentials")
        );
    }
}
