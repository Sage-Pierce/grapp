package org.codegas.users.restresource;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.codegas.commons.lang.value.Email;
import org.codegas.webservices.hal.api.HalLink;
import org.codegas.webservices.hal.api.HalRepresentation;
import org.codegas.webservices.hal.jaxrs.HalJsonResource;
import org.codegas.webservices.hal.jaxrs.HalResourceLinkBuilder;
import org.codegas.users.service.api.UserService;
import org.codegas.users.service.dto.UserDto;

@Path("/users/{email}/")
public class UserResource extends HalJsonResource {

    private final UserService userService;

    @Inject
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GET
    public Response get(@PathParam(value = "email") Email email) {
        return buildHalResponse(asRepresentationOf(userService.get(email)));
    }

    @PUT
    public Response update(@PathParam(value = "email") Email email,
        @QueryParam(value = "name") String name) {
        return buildHalResponse(asRepresentationOf(userService.update(email, name)));
    }

    public static HalLink createRootLink(String rel) {
        return createSelfLinkBuilder().withRel(rel);
    }

    protected static HalRepresentation asRepresentationOf(UserDto userDto) {
        return halRepresentationFactory.createFor(userDto).withLinks(createLinks(userDto));
    }

    private static List<HalLink> createLinks(UserDto userDto) {
        return Collections.singletonList(createSelfLinkBuilder().pathArgs(userDto.getEmail()).withSelfRel());
    }

    private static HalResourceLinkBuilder createSelfLinkBuilder() {
        return HalResourceLinkBuilder.linkTo(UserResource.class).queryParams("name");
    }
}
