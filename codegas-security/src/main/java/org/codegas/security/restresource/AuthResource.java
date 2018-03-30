package org.codegas.security.restresource;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codegas.security.service.api.Authorization;
import org.codegas.security.service.api.AuthorizationException;
import org.codegas.security.service.api.AuthorizationService;

@Path("/auth/")
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    private final AuthorizationService authorizationService;

    @Inject
    public AuthResource(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @GET
    @Path("execute")
    public Response authorize(@QueryParam("redirectUri") String redirectUri) {
        return Response.seeOther(authorizationService.authorize(redirectUri)).build();
    }

    @GET
    public Response logIn(@QueryParam("redirectUri") String redirectUri, @QueryParam("authCode") String authCode) throws AuthorizationException {
        return Response.ok(authorizationService.logIn(redirectUri, authCode)).build();
    }

    @DELETE
    public Response logOut(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorization, @QueryParam("redirectUri") String redirectUri) throws AuthorizationException {
        return Response.seeOther(URI.create(redirectUri))
            .entity(authorizationService.logOut(Authorization.parse(authorization)))
            .build();
    }
}
