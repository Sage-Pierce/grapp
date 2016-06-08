package com.wisegas.shoppinglists.restresource;

import com.wisegas.common.webservices.hal.api.HalLink;
import com.wisegas.common.webservices.hal.api.HalRepresentation;
import com.wisegas.common.webservices.jaxrs.hal.JaxrsHalJsonResource;
import com.wisegas.common.webservices.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import com.wisegas.shoppinglists.service.api.ShoppingListItemService;
import com.wisegas.shoppinglists.service.dto.ShoppingListItemDto;

import javax.inject.Inject;
import javax.ws.rs.*;
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

   @PUT
   public Response update(@PathParam("id") final String id,
                          @QueryParam("obtained") final boolean obtained) {
      return buildHalResponse(asRepresentationOf(shoppingListItemService.update(id, obtained)));
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
      return JaxrsHalResourceLinkBuilder.linkTo(ShoppingListItemResource.class).queryParams("obtained");
   }
}
