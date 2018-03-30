package org.codegas.security.service.api;

public final class Authentication {

    private final boolean secure;

    private final Authorization<String> authorization;

    public Authentication(boolean secure, Authorization<String> authorization) {
        this.secure = secure;
        this.authorization = authorization;
    }

    public boolean isSecure() {
        return secure;
    }

    public String getScheme() {
        return authorization.getScheme();
    }

    public Authorization<String> getAuthorization() {
        return authorization;
    }
}
