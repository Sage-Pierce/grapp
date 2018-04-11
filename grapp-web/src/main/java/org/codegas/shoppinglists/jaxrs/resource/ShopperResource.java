package org.codegas.shoppinglists.jaxrs.resource;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.codegas.commons.lang.value.Email;
import org.codegas.webservice.hal.api.HalLink;
import org.codegas.webservice.hal.api.HalRepresentation;
import org.codegas.webservice.hal.jaxrs.HalJsonResource;
import org.codegas.webservice.hal.jaxrs.HalResourceLinkBuilder;
import org.codegas.shoppinglists.service.api.ShopperService;
import org.codegas.shoppinglists.service.dto.ShopperDto;

@Path("/shoppers/{email}/")
public class ShopperResource extends HalJsonResource {

    private final ShopperService shopperService;

    @Inject
    public ShopperResource(ShopperService shopperService) {
        this.shopperService = shopperService;
    }

    @POST
    @Path("addList")
    public Response addList(@PathParam("email") Email email,
        @QueryParam("name") String name) {
        return buildHalResponse(ShoppingListResource.asRepresentationOf(shopperService.addList(email, name)));
    }

    public static HalLink createRootLink(String rel) {
        return createSelfLinkBuilder().withRel(rel);
    }

    protected static HalRepresentation asRepresentationOf(ShopperDto shopperDto) {
        return halRepresentationFactory.createFor(shopperDto).withLinks(createLinks(shopperDto));
    }

    private static List<HalLink> createLinks(ShopperDto shopperDto) {
        return Arrays.asList(
            createSelfLinkBuilder().pathArgs(shopperDto.getEmail()).withSelfRel(),
            createSelfLinkBuilder().pathArgs(shopperDto.getEmail()).method("addList").queryParams("name").withRel("addList")
        );
    }

    private static HalResourceLinkBuilder createSelfLinkBuilder() {
        return HalResourceLinkBuilder.linkTo(ShopperResource.class);
    }
}
