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
import org.codegas.webservice.hal.api.HalConfig;
import org.codegas.webservice.hal.api.HalLink;
import org.codegas.webservice.hal.api.HalRepresentation;
import org.codegas.webservice.hal.api.HalRepresentationFactory;
import org.codegas.webservice.hal.jaxrs.HalResourceLinkBuilder;
import org.codegas.shoppinglists.service.api.ShopperService;
import org.codegas.shoppinglists.service.dto.ShopperDto;

@Path("/shoppers/{email}/")
public class ShopperResource extends HalJsonResource {

    private final ShopperService shopperService;

    @Inject
    public ShopperResource(HalConfig halConfig, ShopperService shopperService) {
        super(halConfig);
        this.shopperService = shopperService;
    }

    @POST
    @Path("addList")
    public Response addList(@PathParam("email") Email email,
        @QueryParam("name") String name) {
        return buildHalResponse(ShoppingListResource.asRepresentationOf(halRepresentationFactory, shopperService.addList(email, name)));
    }

    protected static HalRepresentation asRepresentationOf(HalRepresentationFactory halRepresentationFactory, ShopperDto shopperDto) {
        return halRepresentationFactory.createFor(shopperDto).withLinks(createLinks(shopperDto));
    }

    protected static HalLink createRootLink(String rel) {
        return createSelfLinkBuilder().withRel(rel);
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
