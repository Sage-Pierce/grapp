package org.codegas.security.restresource;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.codegas.security.service.api.Authorization;
import org.codegas.security.service.api.AuthorizationException;
import org.codegas.security.service.api.AuthorizationService;
import org.codegas.security.service.dto.UserDto;
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

    @GET
    public Response authorize(@QueryParam("redirectUri") String redirectUri) {
        return Response.seeOther(URI.create(authorizationService.authorize(redirectUri))).build();
    }

    @POST
    @Path("logIn")
    public Response logIn(@QueryParam("redirectUri") String redirectUri, @QueryParam("authCode") String authCode) throws AuthorizationException {
        return buildHalResponse(asRepresentationOf(authorizationService.logIn(redirectUri, authCode)));
    }

    @GET
    @Path("authenticate")
    public Response authenticate(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorization) throws AuthorizationException {
        return buildHalResponse(asRepresentationOf(authorizationService.authenticate(Authorization.parse(authorization))));
    }

    @DELETE
    @Path("logOut")
    public Response logOut(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorization, @QueryParam("redirectUri") String redirectUri) throws AuthorizationException {
        return Response.seeOther(URI.create(redirectUri))
            .entity(authorizationService.logOut(Authorization.parse(authorization)))
            .build();
    }

    public static HalLink createRootLink(String rel) {
        return createSelfLinkBuilder().withRel(rel);
    }

    protected static HalRepresentation asRepresentationOf(UserDto userDto) {
        return halRepresentationFactory.createFor(userDto).withLinks(createLinks());
    }

    private static List<HalLink> createLinks() {
        return Arrays.asList(
            createSelfLinkBuilder().withSelfRel(),
            HalResourceLinkBuilder.linkTo(AuthResource.class).method("logIn").queryParams("redirectUri", "authCode").withRel("logIn"),
            HalResourceLinkBuilder.linkTo(AuthResource.class).method("authenticate").withRel("authenticate"),
            HalResourceLinkBuilder.linkTo(AuthResource.class).method("logOut").queryParams("redirectUri").withRel("logOut")
        );
    }

    private static HalResourceLinkBuilder createSelfLinkBuilder() {
        return HalResourceLinkBuilder.linkTo(AuthResource.class).queryParams("redirectUri");
    }
}
