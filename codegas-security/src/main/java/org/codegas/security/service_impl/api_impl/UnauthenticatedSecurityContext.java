package org.codegas.security.service_impl.api_impl;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

public class UnauthenticatedSecurityContext implements SecurityContext {

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
