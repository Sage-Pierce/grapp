package org.codegas.shoppinglists.restresource;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.codegas.commons.webservices.hal.api.HalLink;
import org.codegas.commons.webservices.jaxrs.hal.JaxrsHalJsonResource;
import org.codegas.commons.webservices.jaxrs.hal.JaxrsHalResourceLinkBuilder;

@Path("/shoppingLists/root/")
public class RootResource extends JaxrsHalJsonResource {

    @GET
    public Response get() {
        return buildHalResponse(halRepresentationFactory.createForLinks(createLinks()));
    }

    private static List<HalLink> createLinks() {
        return Arrays.asList(
            JaxrsHalResourceLinkBuilder.linkTo(RootResource.class).withSelfRel(),
            ShoppersResource.createRootLink("shoppers"),
            ShopperResource.createRootLink("shopper"),
            ShoppingListResource.createRootLink("shoppingList"),
            ShoppingListItemResource.createRootLink("shoppingListItem")
        );
    }
}
