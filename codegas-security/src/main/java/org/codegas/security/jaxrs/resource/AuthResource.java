package org.codegas.security.jaxrs.resource;

import java.net.URI;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.codegas.security.service.api.AuthService;
import org.codegas.security.service.api.Authorization;
import org.codegas.security.service.api.AuthorizationException;
import org.codegas.webservice.hal.api.HalLink;
import org.codegas.webservice.hal.api.HalRepresentation;
import org.codegas.webservice.hal.jaxrs.HalResource;
import org.codegas.webservice.hal.jaxrs.HalResourceLinkBuilder;
import org.codegas.webservice.hal.representation.HalJsonRepresentationFactory;

@Path("/auth/")
@Produces(HalJsonRepresentationFactory.HAL_JSON)
public class AuthResource extends HalResource {

    private final AuthService authService;

    @Inject
    public AuthResource(AuthService authService) {
        this.authService = authService;
    }

    @POST
    public Response authorize(@Context UriInfo uriInfo, @QueryParam("appStateUri") String appStateUri) {
        return Response.created(authService.authorize(uriInfo.getAbsolutePath(), URI.create(appStateUri))).build();
    }

    @GET
    public Response callback(@Context UriInfo uriInfo, @QueryParam("state") String appStateUri, @QueryParam("code") String authCode) throws AuthorizationException {
        Authorization authorization = authService.logIn(uriInfo.getAbsolutePath(), authCode);
        String base64Auth = Base64.getUrlEncoder().encodeToString(authorization.toString().getBytes());
        return Response.seeOther(URI.create(appStateUri + "?auth=" + base64Auth)).build();
    }

    @PUT
    public Response reauthenticate(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorization) throws AuthorizationException {
        return buildHalResponse(asRepresentationOf(authService.reauthenticate(Authorization.parse(authorization))));
    }

    protected HalRepresentation asRepresentationOf(Authorization authorization) {
        return new HalJsonRepresentationFactory().createFor(authorization.toString()).withLinks(createLinks());
    }

    private static List<HalLink> createLinks() {
        return Collections.singletonList(createSelfLinkBuilder().withSelfRel());
    }

    private static HalResourceLinkBuilder createSelfLinkBuilder() {
        return HalResourceLinkBuilder.linkTo(AuthResource.class).queryParams("appStateUri");
    }
}
