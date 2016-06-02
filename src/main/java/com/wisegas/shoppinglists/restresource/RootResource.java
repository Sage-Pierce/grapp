package com.wisegas.shoppinglists.restresource;

import com.wisegas.common.webservices.hal.api.HalLink;
import com.wisegas.common.webservices.jaxrs.hal.JaxrsHalJsonResource;
import com.wisegas.common.webservices.jaxrs.hal.JaxrsHalResourceLinkBuilder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Path("/shoppingLists/root/")
public class RootResource extends JaxrsHalJsonResource {

   @GET
   public Response getRoot() {
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
