package org.codegas.security.jaxrs.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import org.codegas.security.service.api.AuthService;
import org.codegas.security.service.api.Authentication;
import org.codegas.security.service.api.Authorization;
import org.codegas.security.service.api.AuthorizationException;
import org.codegas.security.service.api.UserSecurityContextCreator;
import org.codegas.security.service.dto.UserDto;

@Named
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    private static final Collection<String> SECURE_REQUEST_SCHEMES = Collections.singleton("https");

    private final AuthService authService;

    private final UserSecurityContextCreator userSecurityContextCreator;

    @Inject
    public AuthenticationFilter(AuthService authService, UserSecurityContextCreator userSecurityContextCreator) {
        this.authService = authService;
        this.userSecurityContextCreator = userSecurityContextCreator;
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        requestContext.setSecurityContext(Optional.ofNullable(requestContext.getHeaderString(HttpHeaders.AUTHORIZATION))
            .map(Authorization::parse)
            .flatMap(authorization -> authenticate(requestContext, authorization))
            .orElseGet(userSecurityContextCreator::unauthenticated));
    }

    protected final Optional<SecurityContext> authenticate(ContainerRequestContext requestContext, Authorization<String> authorization) {
        try {
            boolean secure = isSecure(requestContext);
            UserDto userDto = authService.authenticate(authorization);
            return Optional.of(userSecurityContextCreator.apply(new Authentication(secure, authorization), userDto));
        } catch (AuthorizationException e) {
            return Optional.empty();
        }
    }

    protected boolean isSecure(ContainerRequestContext requestContext) {
        return SECURE_REQUEST_SCHEMES.contains(requestContext.getUriInfo().getRequestUri().getScheme());
    }
}
