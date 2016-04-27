package com.wisegas.shoppinglist.restresource;

import com.wisegas.common.lang.value.Email;
import com.wisegas.common.webserver.hal.api.HalLink;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalJsonResource;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import com.wisegas.shoppinglist.service.api.ShopperService;

import javax.inject.Inject;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/shoppers/")
public class ShoppersResource extends JaxrsHalJsonResource {

   private final ShopperService shopperService;

   @Inject
   public ShoppersResource(ShopperService shopperService) {
      this.shopperService = shopperService;
   }

   @PUT
   public Response loadByEmail(@QueryParam("email") final Email email) {
      return buildHalResponse(ShopperResource.asRepresentationOf(shopperService.loadByEmail(email)));
   }

   public static HalLink createRootLink(String rel) {
      return createSelfLinkBuilder().withRel(rel);
   }

   private static JaxrsHalResourceLinkBuilder createSelfLinkBuilder() {
      return JaxrsHalResourceLinkBuilder.linkTo(ShoppersResource.class).queryParams("email");
   }
}
