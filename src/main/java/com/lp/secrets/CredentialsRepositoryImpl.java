package com.lp.secrets;

import com.google.cloud.secretmanager.v1.AccessSecretVersionRequest;
import com.google.cloud.secretmanager.v1.SecretManagerServiceClient;
import com.google.cloud.secretmanager.v1.SecretPayload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class CredentialsRepositoryImpl implements CredentialsRepository {
    @Value("${google.cloud.projectId}")
    private String googleProjectId;

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