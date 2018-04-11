package org.codegas.security.jaxrs.resource;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.codegas.security.service.api.Authorization;
import org.codegas.security.service.api.AuthorizationException;
import org.codegas.security.service.api.AuthService;
import org.codegas.security.service.dto.UserDto;
import org.codegas.webservice.hal.api.HalLink;
import org.codegas.webservice.hal.api.HalRepresentation;
import org.codegas.webservice.hal.jaxrs.HalJsonResource;
import org.codegas.webservice.hal.jaxrs.HalResourceLinkBuilder;

@Path("/auth/user/")
public class AuthUserResource extends HalJsonResource {

    private final AuthService authService;

    @Inject
    public AuthUserResource(AuthService authService) {
        this.authService = authService;
    }

    @GET
    public Response authenticate(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorization) throws AuthorizationException {
        return buildHalResponse(asRepresentationOf(authService.authenticate(Authorization.parse(authorization))));
    }

    @DELETE
    public Response logOut(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorization) throws AuthorizationException {
        return buildHalResponse(asRepresentationOf(authService.logOut(Authorization.parse(authorization))));
    }

    public static HalLink createRootLink(String rel) {
        return createSelfLinkBuilder().withRel(rel);
    }

    protected static HalRepresentation asRepresentationOf(UserDto userDto) {
        return halRepresentationFactory.createFor(userDto).withLinks(createLinks());
    }

    private static List<HalLink> createLinks() {
        return Collections.singletonList(createSelfLinkBuilder().withSelfRel());
    }

    private static HalResourceLinkBuilder createSelfLinkBuilder() {
        return HalResourceLinkBuilder.linkTo(AuthUserResource.class).queryParams("redirectUri");
    }
}
