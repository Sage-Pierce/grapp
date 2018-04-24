package org.codegas.itemmanagement.jaxrs.resource;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.codegas.webservice.hal.api.HalLink;
import org.codegas.webservice.hal.jaxrs.HalResourceLinkBuilder;
import org.codegas.itemmanagement.service.api.ItemService;

@Path("/items/")
public class ItemsResource extends HalJsonResource {

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
        return buildHalResponse(ItemResource.asRepresentationOf(halRepresentationFactory, itemService.createSubItem(superItemCode, codeType, code, name)));
    }

    @GET
    public Response get() {
        return buildHalResponse(halRepresentationFactory.createFor(itemService.get()).withLinks(createLinks()));
    }

    protected static HalLink createRootLink(String rel) {
        return createSelfLinkBuilder().withRel(rel);
    }

    private static List<HalLink> createLinks() {
        return Collections.singletonList(createSelfLinkBuilder().withSelfRel());
    }

    private static HalResourceLinkBuilder createSelfLinkBuilder() {
        return HalResourceLinkBuilder.linkTo(ItemsResource.class).queryParams("superItemCode", "codeType", "code", "name");
    }
}