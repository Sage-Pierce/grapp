package org.codegas.itemmanagement.restresource;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.codegas.commons.webservices.hal.api.HalLink;
import org.codegas.commons.webservices.hal.jaxrs.JaxrsHalJsonResource;
import org.codegas.commons.webservices.hal.jaxrs.JaxrsHalResourceLinkBuilder;
import org.codegas.itemmanagement.service.api.ItemService;

@Path("/items/")
public class ItemsResource extends JaxrsHalJsonResource {

    private final ItemService itemService;

    @Inject
    public ItemsResource(ItemService itemService) {
        this.itemService = itemService;
    }

    @POST
    public Response createSubItem(@QueryParam("superItemCode") String superItemCode,
        @QueryParam("codeType") String codeType,
        @QueryParam("code") String code,
        @QueryParam("name") String name) {
        return buildHalResponse(ItemResource.asRepresentationOf(itemService.createSubItem(superItemCode, codeType, code, name)));
    }

    @GET
    public Response get() {
        return buildHalResponse(halRepresentationFactory.createFor(itemService.get()).withLinks(createLinks()));
    }

    public static HalLink createRootLink(String rel) {
        return createSelfLinkBuilder().withRel(rel);
    }

    private static List<HalLink> createLinks() {
        return Collections.singletonList(createSelfLinkBuilder().withSelfRel());
    }

    private static JaxrsHalResourceLinkBuilder createSelfLinkBuilder() {
        return JaxrsHalResourceLinkBuilder.linkTo(ItemsResource.class).queryParams("superItemCode", "codeType", "code", "name");
    }
}
