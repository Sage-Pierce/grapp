package org.codegas.shoppinglists.jaxrs.resource;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.codegas.commons.lang.value.PrincipalName;
import org.codegas.shoppinglists.service.api.ShopperService;
import org.codegas.shoppinglists.service.dto.ShopperDto;
import org.codegas.webservice.hal.api.HalConfig;
import org.codegas.webservice.hal.api.HalLink;
import org.codegas.webservice.hal.api.HalRepresentation;
import org.codegas.webservice.hal.api.HalRepresentationFactory;
import org.codegas.webservice.hal.jaxrs.HalResourceLinkBuilder;

@Path("/shoppers/")
public class ShopperResource extends HalJsonResource {

    private final ShopperService shopperService;

    @Inject
    public ShopperResource(HalConfig halConfig, ShopperService shopperService) {
        super(halConfig);
        this.shopperService = shopperService;
    }

    @PUT
    public Response load(@Context SecurityContext securityContext) {
        PrincipalName shopperName = PrincipalName.fromPrincipal(securityContext.getUserPrincipal());
        return buildHalResponse(ShopperResource.asRepresentationOf(halRepresentationFactory, shopperService.load(shopperName)));
    }

    @POST
    @Path("addList")
    public Response addList(@Context SecurityContext securityContext, @QueryParam("name") String name) {
        PrincipalName shopperName = PrincipalName.fromPrincipal(securityContext.getUserPrincipal());
        return buildHalResponse(ShoppingListResource.asRepresentationOf(halRepresentationFactory, shopperService.addList(shopperName, name)));
    }

    protected static HalRepresentation asRepresentationOf(HalRepresentationFactory halRepresentationFactory, ShopperDto shopperDto) {
        return halRepresentationFactory.createFor(shopperDto).withLinks(createLinks(shopperDto));
    }

    protected static HalLink createRootLink(String rel) {
        return createSelfLinkBuilder().withRel(rel);
    }

    private static List<HalLink> createLinks(ShopperDto shopperDto) {
        return Arrays.asList(
            createSelfLinkBuilder().pathArgs(shopperDto.getName()).withSelfRel(),
            createSelfLinkBuilder().pathArgs(shopperDto.getName()).method("addList").queryParams("name").withRel("addList")
        );
    }

    private static HalResourceLinkBuilder createSelfLinkBuilder() {
        return HalResourceLinkBuilder.linkTo(ShopperResource.class);
    }
}
