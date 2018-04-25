package org.codegas.shoppinglists.jaxrs.resource;

import javax.inject.Inject;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.codegas.commons.lang.value.Email;
import org.codegas.webservice.hal.api.HalConfig;
import org.codegas.webservice.hal.api.HalLink;
import org.codegas.webservice.hal.jaxrs.HalResourceLinkBuilder;
import org.codegas.shoppinglists.service.api.ShopperService;

@Path("/shoppers/")
public class ShoppersResource extends HalJsonResource {

    private final ShopperService shopperService;

    @Inject
    public ShoppersResource(HalConfig halConfig, ShopperService shopperService) {
        super(halConfig);
        this.shopperService = shopperService;
    }

    @PUT
    public Response loadByEmail(@QueryParam("email") Email email) {
        return buildHalResponse(ShopperResource.asRepresentationOf(halRepresentationFactory, shopperService.loadByEmail(email)));
    }

    protected static HalLink createRootLink(String rel) {
        return createSelfLinkBuilder().withRel(rel);
    }

    private static HalResourceLinkBuilder createSelfLinkBuilder() {
        return HalResourceLinkBuilder.linkTo(ShoppersResource.class).queryParams("email");
    }
}
