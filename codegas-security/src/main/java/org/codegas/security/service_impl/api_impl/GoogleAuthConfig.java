package org.codegas.security.service_impl.api_impl;

import com.google.api.client.http.HttpTransport;

public final class GoogleAuthConfig {

    private final HttpTransport httpTransport;

    private final String clientId;

    private final String clientSecret;

    public GoogleAuthConfig(HttpTransport httpTransport, String clientId, String clientSecret) {
        this.httpTransport = httpTransport;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public HttpTransport getHttpTransport() {
        return httpTransport;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }
}
