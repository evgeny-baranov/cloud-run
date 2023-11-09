package com.lp.secrets;

import com.google.cloud.secretmanager.v1.AccessSecretVersionRequest;
import com.google.cloud.secretmanager.v1.SecretManagerServiceClient;
import com.google.cloud.secretmanager.v1.SecretPayload;
import org.springframework.beans.factory.annotation.Value;

public class CredentialsRepositoryImpl implements CredentialsRepository {
    @Value("${spring.cloud.gcp.secretmanager.project-id}")
    private String googleProjectId;

    public CredentialsRepositoryImpl() {

    }

    public String getSecretData(String secretName) throws Exception {
        try (SecretManagerServiceClient client = SecretManagerServiceClient.create()) {
            AccessSecretVersionRequest accessRequest = AccessSecretVersionRequest.newBuilder().setName(getSecretId(secretName)).build();
            SecretPayload secretPayload = client.accessSecretVersion(accessRequest).getPayload();
            return secretPayload.getData().toStringUtf8();
        }
    }

    private String getSecretId(String secretName) {
        return "projects/"
                + googleProjectId
                + "/com/lp/secrets/"
                + secretName
                + "/versions/latest";
    }
}