package org.codegas.security.restresource;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.codegas.security.service.api.Authorization;
import org.codegas.security.service.api.AuthorizationException;
import org.codegas.security.service.api.AuthorizationService;
import org.codegas.webservices.hal.api.HalLink;
import org.codegas.webservices.hal.api.HalRepresentation;
import org.codegas.webservices.hal.jaxrs.HalJsonResource;
import org.codegas.webservices.hal.jaxrs.HalResourceLinkBuilder;

@Path("/auth/")
public class AuthResource extends HalJsonResource {

    private final AuthorizationService authorizationService;

    @Inject
    public AuthResource(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @POST
    public Response authorize(@QueryParam("redirectUri") String redirectUri) {
        return Response.created(authorizationService.authorize(redirectUri)).build();
    }

    @PUT
    public Response logIn(@QueryParam("redirectUri") String redirectUri, @QueryParam("authCode") String authCode) throws AuthorizationException {
        return buildHalResponse(asRepresentationOf(authorizationService.logIn(redirectUri, authCode)));
    }

    public static HalLink createRootLink(String rel) {
        return createSelfLinkBuilder().withRel(rel);
    }

    protected static HalRepresentation asRepresentationOf(Authorization authorization) {
        return halRepresentationFactory.createFor(authorization.toString()).withLinks(createLinks());
    }

    private static List<HalLink> createLinks() {
        return Collections.singletonList(createSelfLinkBuilder().withSelfRel());
    }

    private static HalResourceLinkBuilder createSelfLinkBuilder() {
        return HalResourceLinkBuilder.linkTo(AuthResource.class).queryParams("redirectUri", "authCode");
    }
}
