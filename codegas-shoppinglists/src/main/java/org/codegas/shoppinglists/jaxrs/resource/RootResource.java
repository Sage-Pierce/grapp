package org.codegas.shoppinglists.jaxrs.resource;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.codegas.webservice.hal.api.HalLink;
import org.codegas.webservice.hal.jaxrs.HalResourceLinkBuilder;

@Path("/shoppingLists/root/")
public class RootResource extends HalJsonResource {

    @GET
    public Response get() {
        return buildHalResponse(halRepresentationFactory.createForLinks(createLinks()));
    }

    private static List<HalLink> createLinks() {
        return Arrays.asList(
            HalResourceLinkBuilder.linkTo(RootResource.class).withSelfRel(),
            ShoppersResource.createRootLink("shoppers"),
            ShopperResource.createRootLink("shopper"),
            ShoppingListResource.createRootLink("shoppingList"),
            ShoppingListItemResource.createRootLink("shoppingListItem")
        );
    }
}