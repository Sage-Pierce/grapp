package org.codegas.security.restfilter;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

import org.codegas.security.service.api.AuthorizationService;

@Named
@Provider
@PreMatching
public class AuthenticationFilter implements ContainerRequestFilter {

    private final AuthorizationService authorizationService;

    @Inject
    public AuthenticationFilter(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authorization = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        String requestScheme = requestContext.getUriInfo().getRequestUri().getScheme();
        requestContext.setSecurityContext(authorizationService.authenticate(requestScheme, authorization));
    }
}
