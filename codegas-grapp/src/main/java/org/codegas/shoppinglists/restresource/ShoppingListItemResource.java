package org.codegas.shoppinglists.restresource;

import java.util.Collections;
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
import org.codegas.commons.webservices.hal.jaxrs.HalJsonResource;
import org.codegas.commons.webservices.hal.jaxrs.HalResourceLinkBuilder;
import org.codegas.shoppinglists.service.api.ShoppingListItemService;
import org.codegas.shoppinglists.service.dto.ShoppingListItemDto;

@Path("/shoppingListItem/{id}/")
public class ShoppingListItemResource extends HalJsonResource {

    private final ShoppingListItemService shoppingListItemService;

    @Inject
    public ShoppingListItemResource(ShoppingListItemService shoppingListItemService) {
        this.shoppingListItemService = shoppingListItemService;
    }

    @GET
    public Response get(@PathParam("id") String id) {
        return buildHalResponse(asRepresentationOf(shoppingListItemService.get(id)));
    }

    @PUT
    public Response update(@PathParam("id") String id,
        @QueryParam("obtained") boolean obtained) {
        return buildHalResponse(asRepresentationOf(shoppingListItemService.update(id, obtained)));
    }

    @DELETE
    public Response delete(@PathParam("id") String id) {
        shoppingListItemService.delete(id);
        return Response.ok().build();
    }

    public static HalLink createRootLink(String rel) {
        return createSelfLinkBuilder().withRel(rel);
    }

    protected static HalRepresentation asRepresentationOf(ShoppingListItemDto shoppingListItemDto) {
        return halRepresentationFactory.createFor(shoppingListItemDto).withLinks(createLinks(shoppingListItemDto));
    }

    private static List<HalLink> createLinks(ShoppingListItemDto shoppingListItemDto) {
        return Collections.singletonList(createSelfLinkBuilder().pathArgs(shoppingListItemDto.getId()).withSelfRel());
    }

    private static HalResourceLinkBuilder createSelfLinkBuilder() {
        return HalResourceLinkBuilder.linkTo(ShoppingListItemResource.class).queryParams("obtained");
    }
}
