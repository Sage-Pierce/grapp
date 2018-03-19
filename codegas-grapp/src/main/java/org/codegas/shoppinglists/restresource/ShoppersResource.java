package org.codegas.shoppinglists.restresource;

import javax.inject.Inject;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.codegas.commons.lang.value.Email;
import org.codegas.commons.webservices.hal.api.HalLink;
import org.codegas.commons.webservices.hal.jaxrs.JaxrsHalJsonResource;
import org.codegas.commons.webservices.hal.jaxrs.JaxrsHalResourceLinkBuilder;
import org.codegas.shoppinglists.service.api.ShopperService;

@Path("/shoppers/")
public class ShoppersResource extends JaxrsHalJsonResource {

    private final ShopperService shopperService;

    @Inject
    public ShoppersResource(ShopperService shopperService) {
        this.shopperService = shopperService;
    }

    @PUT
    public Response loadByEmail(@QueryParam("email") Email email) {
        return buildHalResponse(ShopperResource.asRepresentationOf(shopperService.loadByEmail(email)));
    }

    public static HalLink createRootLink(String rel) {
        return createSelfLinkBuilder().withRel(rel);
    }

    private static JaxrsHalResourceLinkBuilder createSelfLinkBuilder() {
        return JaxrsHalResourceLinkBuilder.linkTo(ShoppersResource.class).queryParams("email");
    }
}
