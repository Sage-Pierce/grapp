package org.codegas.security.service_impl.api_impl;

import java.security.Principal;
import java.util.Collections;
import java.util.Set;

import javax.ws.rs.core.SecurityContext;

import org.codegas.security.domain.entity.User;

public class AuthenticatedUserSecurityContext implements SecurityContext {

    private static final Set<String> SECURE_REQUEST_SCHEMES = Collections.singleton("https");

    private final String requestScheme;

    private final String authenticationScheme;

    private final User user;

    public AuthenticatedUserSecurityContext(String requestScheme, String authenticationScheme, User user) {
        this.requestScheme = requestScheme;
        this.authenticationScheme = authenticationScheme;
        this.user = user;
    }

    @Override
    public Principal getUserPrincipal() {
        return user;
    }

    @Override
    public boolean isUserInRole(String role) {
        return user.isInRole(role);
    }

    @Override
    public boolean isSecure() {
        return SECURE_REQUEST_SCHEMES.contains(requestScheme);
    }

    @Override
    public String getAuthenticationScheme() {
        return authenticationScheme;
    }
}
