package com.wisegas.stores.restresource;

import com.wisegas.common.webservices.hal.api.HalLink;
import com.wisegas.common.webservices.hal.api.HalRepresentation;
import com.wisegas.common.webservices.jaxrs.hal.JaxrsHalJsonResource;
import com.wisegas.common.webservices.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import com.wisegas.stores.service.api.ShoppingLayoutService;
import com.wisegas.stores.service.dto.ShoppingLayoutDto;
import com.wisegas.stores.service.dto.ShoppingListDto;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Path("/storeLayouts/{id}/shopping")
public class ShoppingLayoutResource extends JaxrsHalJsonResource {

   private final ShoppingLayoutService shoppingLayoutService;

   @Inject
   public ShoppingLayoutResource(ShoppingLayoutService shoppingLayoutService) {
      this.shoppingLayoutService = shoppingLayoutService;
   }

   @GET
   public Response getShoppingLayout(@PathParam("id") String id,
                                     @QueryParam("shoppingList") ShoppingListDto shoppingListDto) {
      return buildHalResponse(asRepresentationOf(shoppingLayoutService.getShoppingLayout(id, shoppingListDto)));
   }

   public static HalLink createRootLink(String rel) {
      return createSelfLinkBuilder().withRel(rel);
   }

   protected static HalRepresentation asRepresentationOf(ShoppingLayoutDto shoppingLayoutDto) {
      return halRepresentationFactory.createFor(shoppingLayoutDto).withLinks(createLinks(shoppingLayoutDto));
   }

   private static List<HalLink> createLinks(ShoppingLayoutDto shoppingLayoutDto) {
      return Collections.singletonList(createSelfLinkBuilder().pathArgs(shoppingLayoutDto.getId()).withSelfRel());
   }

   private static JaxrsHalResourceLinkBuilder createSelfLinkBuilder() {
      return JaxrsHalResourceLinkBuilder.linkTo(ShoppingLayoutResource.class).queryParams("shoppingList");
   }
}
