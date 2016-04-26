package com.wisegas.shoppinglist.restresource;

import com.wisegas.common.lang.value.CodeName;
import com.wisegas.common.webserver.hal.api.HalLink;
import com.wisegas.common.webserver.hal.api.HalRepresentation;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalJsonResource;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import com.wisegas.shoppinglist.service.api.ShoppingListService;
import com.wisegas.shoppinglist.service.dto.ShoppingListDto;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Path("/shoppingLists/{id}/")
public class ShoppingListResource extends JaxrsHalJsonResource {

   private final ShoppingListService shoppingListService;

   @Inject
   public ShoppingListResource(ShoppingListService shoppingListService) {
      this.shoppingListService = shoppingListService;
   }

   @GET
   public Response get(@PathParam("id") final String id) {
      return buildHalResponse(asRepresentationOf(shoppingListService.get(id)));
   }

   @POST
   @Path("addPath")
   public Response addItem(@PathParam("id") final String id,
                           @QueryParam("item") final CodeName item) {
      return buildHalResponse(ShoppingListItemResource.asRepresentationOf(shoppingListService.addItem(id, item)));
   }

   @DELETE
   public Response delete(@PathParam("id") final String id) {
      shoppingListService.delete(id);
      return Response.ok().build();
   }

   public static HalLink createRootLink(String rel) {
      return createSelfLinkBuilder().withRel(rel);
   }

   protected static HalRepresentation asRepresentationOf(ShoppingListDto shoppingListDto) {
      return halRepresentationFactory.createFor(shoppingListDto).withLinks(createLinks(shoppingListDto));
   }

   private static List<HalLink> createLinks(ShoppingListDto shoppingListDto) {
      return Collections.singletonList(createSelfLinkBuilder().pathArgs(shoppingListDto.getId()).withSelfRel());
   }

   private static JaxrsHalResourceLinkBuilder createSelfLinkBuilder() {
      return JaxrsHalResourceLinkBuilder.linkTo(ShopperResource.class);
   }
}
