package org.codegas.security.service.api;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

public final class UnauthenticatedSecurityContext implements SecurityContext {

    @Override
    public Principal getUserPrincipal() {
        return null;
    }

    @Override
    public boolean isUserInRole(String role) {
        return false;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public String getAuthenticationScheme() {
        return null;
    }
}
