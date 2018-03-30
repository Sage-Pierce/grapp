package org.codegas.security.restresource;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    public Response authorize(@QueryParam("redirectUri") String redirectUri) {
        return Response.seeOther(authorizationService.authorize(redirectUri)).build();
    }

    @Path("logIn")
    @GET
    public Response logIn(@QueryParam("redirectUri") String redirectUri, @QueryParam("authCode") String authCode) {
        return Response.ok(authorizationService.logIn(redirectUri, authCode)).build();
    }
}
