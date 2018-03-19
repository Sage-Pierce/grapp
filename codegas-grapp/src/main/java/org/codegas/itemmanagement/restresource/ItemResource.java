package org.codegas.itemmanagement.restresource;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.codegas.commons.webservices.hal.api.HalLink;
import org.codegas.commons.webservices.hal.api.HalRepresentation;
import org.codegas.commons.webservices.hal.jaxrs.JaxrsHalJsonResource;
import org.codegas.commons.webservices.hal.jaxrs.JaxrsHalResourceLinkBuilder;
import org.codegas.itemmanagement.service.api.ItemService;
import org.codegas.itemmanagement.service.dto.ItemDto;

@Path("/items/{primaryCode}/")
public class ItemResource extends JaxrsHalJsonResource {

    private final ItemService itemService;

    @Inject
    public ItemResource(ItemService itemService) {
        this.itemService = itemService;
    }

    @GET
    public Response get(@PathParam("primaryCode") String primaryCode) {
        return buildHalResponse(asRepresentationOf(itemService.get(primaryCode)));
    }

    @PUT
    public Response update(@PathParam("primaryCode") String primaryCode,
        @QueryParam("name") String name) {
        return buildHalResponse(asRepresentationOf(itemService.update(primaryCode, name)));
    }

    @PUT
    @Path("makeGeneral")
    public Response makeGeneral(@PathParam("primaryCode") String primaryCode) {
        return buildHalResponse(asRepresentationOf(itemService.makeGeneral(primaryCode)));
    }

    @PUT
    @Path("move")
    public Response move(@PathParam("primaryCode") String primaryCode,
        @QueryParam("superItemCode") String superItemCode) {
        return buildHalResponse(asRepresentationOf(itemService.move(primaryCode, superItemCode)));
    }

    @DELETE
    public Response delete(@PathParam("primaryCode") String primaryCode) {
        itemService.delete(primaryCode);
        return Response.ok().build();
    }

    public static HalLink createRootLink(String rel) {
        return createSelfLinkBuilder().withRel(rel);
    }

    protected static HalRepresentation asRepresentationOf(ItemDto itemDto) {
        return halRepresentationFactory.createFor(itemDto).withLinks(createLinks(itemDto));
    }

    private static List<HalLink> createLinks(ItemDto itemDto) {
        return Arrays.asList(
            createSelfLinkBuilder().pathArgs(itemDto.getPrimaryCode()).withSelfRel(),
            JaxrsHalResourceLinkBuilder.linkTo(ItemResource.class).method("makeGeneral").pathArgs(itemDto.getPrimaryCode()).withRel("makeGeneral"),
            JaxrsHalResourceLinkBuilder.linkTo(ItemResource.class).method("move").pathArgs(itemDto.getPrimaryCode()).queryParams("superItemCode").withRel("move")
        );
    }

    private static JaxrsHalResourceLinkBuilder createSelfLinkBuilder() {
        return JaxrsHalResourceLinkBuilder.linkTo(ItemResource.class).queryParams("name");
    }
}
