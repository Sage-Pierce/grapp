package org.codegas.security.service.api;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

public final class AuthenticatedSecurityContext implements SecurityContext {

    private final Authentication authentication;

    private final UserPrincipal userPrincipal;

    public AuthenticatedSecurityContext(Authentication authentication, UserPrincipal userPrincipal) {
        this.authentication = authentication;
        this.userPrincipal = userPrincipal;
    }

    @Override
    public Principal getUserPrincipal() {
        return userPrincipal;
    }

    @Override
    public boolean isUserInRole(String role) {
        return userPrincipal.hasRole(role);
    }

    @Override
    public boolean isSecure() {
        return authentication.isSecure();
    }

    @Override
    public String getAuthenticationScheme() {
        return authentication.getScheme();
    }
}
