package org.codegas.stores.restresource;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.codegas.commons.webservices.hal.api.HalLink;
import org.codegas.commons.webservices.hal.api.HalRepresentation;
import org.codegas.commons.webservices.jaxrs.hal.JaxrsHalJsonResource;
import org.codegas.commons.webservices.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import org.codegas.stores.service.api.ShoppingLayoutService;
import org.codegas.stores.service.dto.ShoppingLayoutDto;
import org.codegas.stores.service.dto.ShoppingListDto;

@Path("/storeLayouts/{id}/shopping")
public class ShoppingLayoutResource extends JaxrsHalJsonResource {

    private final ShoppingLayoutService shoppingLayoutService;

    @Inject
    public ShoppingLayoutResource(ShoppingLayoutService shoppingLayoutService) {
        this.shoppingLayoutService = shoppingLayoutService;
    }

    @GET
    public Response getShoppingLayout(@PathParam("id") String id,
        @QueryParam("shoppingList") ShoppingListDto shoppingListDto) {
        return buildHalResponse(asRepresentationOf(shoppingLayoutService.getShoppingLayout(id, shoppingListDto)));
    }

    public static HalLink createRootLink(String rel) {
        return createSelfLinkBuilder().withRel(rel);
    }

    protected static HalRepresentation asRepresentationOf(ShoppingLayoutDto shoppingLayoutDto) {
        return halRepresentationFactory.createFor(shoppingLayoutDto).withLinks(createLinks(shoppingLayoutDto));
    }

    private static List<HalLink> createLinks(ShoppingLayoutDto shoppingLayoutDto) {
        return Collections.singletonList(createSelfLinkBuilder().pathArgs(shoppingLayoutDto.getId()).withSelfRel());
    }

    private static JaxrsHalResourceLinkBuilder createSelfLinkBuilder() {
        return JaxrsHalResourceLinkBuilder.linkTo(ShoppingLayoutResource.class).queryParams("shoppingList");
    }
}
