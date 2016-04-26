package com.wisegas.shoppinglist.restresource;

import com.wisegas.common.webserver.hal.api.HalLink;
import com.wisegas.common.webserver.hal.api.HalRepresentation;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalJsonResource;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import com.wisegas.shoppinglist.service.api.ShoppingListItemService;
import com.wisegas.shoppinglist.service.dto.ShoppingListItemDto;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Path("/shoppingListItem/{id}/")
public class ShoppingListItemResource extends JaxrsHalJsonResource {

   private final ShoppingListItemService shoppingListItemService;

   @Inject
   public ShoppingListItemResource(ShoppingListItemService shoppingListItemService) {
      this.shoppingListItemService = shoppingListItemService;
   }

   @GET
   public Response get(@PathParam("id") final String id) {
      return buildHalResponse(asRepresentationOf(shoppingListItemService.get(id)));
   }

   @DELETE
   public Response delete(@PathParam("id") final String id) {
      shoppingListItemService.delete(id);
      return Response.ok().build();
   }

   public static HalLink createRootLink(String rel) {
      return createSelfLinkBuilder().withRel(rel);
   }

   protected static HalRepresentation asRepresentationOf(ShoppingListItemDto shoppingListItemDto) {
      return halRepresentationFactory.createFor(shoppingListItemDto).withLinks(createLinks(shoppingListItemDto));
   }

   private static List<HalLink> createLinks(ShoppingListItemDto shoppingListItemDto) {
      return Collections.singletonList(createSelfLinkBuilder().pathArgs(shoppingListItemDto.getId()).withSelfRel());
   }

   private static JaxrsHalResourceLinkBuilder createSelfLinkBuilder() {
      return JaxrsHalResourceLinkBuilder.linkTo(ShopperResource.class);
   }
}
