package com.wisegas.shoppinglist.restresource;

import com.wisegas.common.lang.value.Email;
import com.wisegas.common.webservices.hal.api.HalLink;
import com.wisegas.common.webservices.hal.api.HalRepresentation;
import com.wisegas.common.webservices.jaxrs.hal.JaxrsHalJsonResource;
import com.wisegas.common.webservices.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import com.wisegas.shoppinglist.service.api.ShopperService;
import com.wisegas.shoppinglist.service.dto.ShopperDto;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Path("/shoppers/{email}/")
public class ShopperResource extends JaxrsHalJsonResource {

   private final ShopperService shopperService;

   @Inject
   public ShopperResource(ShopperService shopperService) {
      this.shopperService = shopperService;
   }

   @POST
   @Path("addList")
   public Response addList(@PathParam("email") final Email email,
                           @QueryParam("name") final String name) {
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

   private static JaxrsHalResourceLinkBuilder createSelfLinkBuilder() {
      return JaxrsHalResourceLinkBuilder.linkTo(ShopperResource.class);
   }
}
