package org.codegas.users.restresource;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.codegas.commons.webservices.hal.api.HalLink;
import org.codegas.commons.webservices.hal.jaxrs.HalJsonResource;
import org.codegas.commons.webservices.hal.jaxrs.HalResourceLinkBuilder;

@Path("/users/root/")
public class RootResource extends HalJsonResource {

    @GET
    public Response get() {
        return buildHalResponse(halRepresentationFactory.createForLinks(createLinks()));
    }

    private static List<HalLink> createLinks() {
        return Arrays.asList(
            HalResourceLinkBuilder.linkTo(RootResource.class).withSelfRel(),
            LoginResource.createRootLink("logIn"),
            UserResource.createRootLink("user")
        );
    }
}
