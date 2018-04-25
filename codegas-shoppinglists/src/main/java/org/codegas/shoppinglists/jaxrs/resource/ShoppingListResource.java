package org.codegas.shoppinglists.jaxrs.resource;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.codegas.commons.lang.value.CodeName;
import org.codegas.webservice.hal.api.HalConfig;
import org.codegas.webservice.hal.api.HalLink;
import org.codegas.webservice.hal.api.HalRepresentation;
import org.codegas.webservice.hal.api.HalRepresentationFactory;
import org.codegas.webservice.hal.jaxrs.HalResourceLinkBuilder;
import org.codegas.shoppinglists.service.api.ShoppingListService;
import org.codegas.shoppinglists.service.dto.ShoppingListDto;

@Path("/shoppingLists/{id}/")
public class ShoppingListResource extends HalJsonResource {

    private final ShoppingListService shoppingListService;

    @Inject
    public ShoppingListResource(HalConfig halConfig, ShoppingListService shoppingListService) {
        super(halConfig);
        this.shoppingListService = shoppingListService;
    }

    @GET
    public Response get(@PathParam("id") String id) {
        return buildHalResponse(asRepresentationOf(shoppingListService.get(id)));
    }

    @POST
    @Path("addItem")
    public Response addItem(@PathParam("id") String id,
        @QueryParam("item") CodeName item) {
        return buildHalResponse(ShoppingListItemResource.asRepresentationOf(halRepresentationFactory, shoppingListService.addItem(id, item)));
    }

    @DELETE
    public Response delete(@PathParam("id") String id) {
        shoppingListService.delete(id);
        return Response.ok().build();
    }

    protected HalRepresentation asRepresentationOf(ShoppingListDto shoppingListDto) {
        return asRepresentationOf(halRepresentationFactory, shoppingListDto);
    }

    protected static HalRepresentation asRepresentationOf(HalRepresentationFactory halRepresentationFactory, ShoppingListDto shoppingListDto) {
        return halRepresentationFactory.createFor(shoppingListDto).withLinks(createLinks(shoppingListDto));
    }

    protected static HalLink createRootLink(String rel) {
        return createSelfLinkBuilder().withRel(rel);
    }

    private static List<HalLink> createLinks(ShoppingListDto shoppingListDto) {
        return Arrays.asList(
            createSelfLinkBuilder().pathArgs(shoppingListDto.getId()).withSelfRel(),
            createSelfLinkBuilder().pathArgs(shoppingListDto.getId()).method("addItem").queryParams("item").withRel("addItem")
        );
    }

    private static HalResourceLinkBuilder createSelfLinkBuilder() {
        return HalResourceLinkBuilder.linkTo(ShoppingListResource.class);
    }
}
