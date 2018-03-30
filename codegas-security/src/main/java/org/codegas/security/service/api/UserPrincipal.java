package org.codegas.security.service.api;

import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

public final class UserPrincipal implements Principal {

    private final String name;

    private final Collection<String> roles;

    public UserPrincipal(String name, Collection<String> roles) {
        this.name = Objects.requireNonNull(name);
        this.roles = new HashSet<>(roles);
    }

    @Override
    public String getName() {
        return name;
    }

    public boolean hasRole(String role) {
        return roles.contains(role);
    }
}
