package org.codegas.stores.jaxrs.resource;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.codegas.webservice.hal.api.HalLink;
import org.codegas.webservice.hal.api.HalRepresentation;
import org.codegas.webservice.hal.api.HalRepresentationFactory;
import org.codegas.webservice.hal.jaxrs.HalResourceLinkBuilder;
import org.codegas.stores.service.api.ShoppingLayoutService;
import org.codegas.stores.service.dto.ShoppingLayoutDto;
import org.codegas.stores.service.dto.ShoppingListDto;

@Path("/storeLayouts/{id}/shopping")
public class ShoppingLayoutResource extends HalJsonResource {

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

    protected HalRepresentation asRepresentationOf(ShoppingLayoutDto shoppingLayoutDto) {
        return asRepresentationOf(halRepresentationFactory, shoppingLayoutDto);
    }

    protected static HalRepresentation asRepresentationOf(HalRepresentationFactory halRepresentationFactory, ShoppingLayoutDto shoppingLayoutDto) {
        return halRepresentationFactory.createFor(shoppingLayoutDto).withLinks(createLinks(shoppingLayoutDto));
    }

    protected static HalLink createRootLink(String rel) {
        return createSelfLinkBuilder().withRel(rel);
    }

    private static List<HalLink> createLinks(ShoppingLayoutDto shoppingLayoutDto) {
        return Collections.singletonList(createSelfLinkBuilder().pathArgs(shoppingLayoutDto.getId()).withSelfRel());
    }

    private static HalResourceLinkBuilder createSelfLinkBuilder() {
        return HalResourceLinkBuilder.linkTo(ShoppingLayoutResource.class).queryParams("shoppingList");
    }
}
