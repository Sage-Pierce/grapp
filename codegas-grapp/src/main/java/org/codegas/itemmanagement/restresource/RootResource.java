package org.codegas.itemmanagement.restresource;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.codegas.commons.webservices.hal.api.HalLink;
import org.codegas.commons.webservices.hal.jaxrs.JaxrsHalJsonResource;
import org.codegas.commons.webservices.hal.jaxrs.JaxrsHalResourceLinkBuilder;

@Path("/itemManagement/root/")
public class RootResource extends JaxrsHalJsonResource {

    @GET
    public Response get() {
        return buildHalResponse(halRepresentationFactory.createForLinks(createLinks()));
    }

    private static List<HalLink> createLinks() {
        return Arrays.asList(
            JaxrsHalResourceLinkBuilder.linkTo(RootResource.class).withSelfRel(),
            GeneralItemsResource.createRootLink("generalItems"),
            ItemsResource.createRootLink("items"),
            ItemImportResource.createRootLink("importItems"),
            ItemResource.createRootLink("item")
        );
    }
}
