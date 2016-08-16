package com.wisegas.shoppinglists.restresource;

import com.wisegas.common.lang.value.CodeName;
import com.wisegas.common.webservices.hal.api.HalLink;
import com.wisegas.common.webservices.hal.api.HalRepresentation;
import com.wisegas.common.webservices.jaxrs.hal.JaxrsHalJsonResource;
import com.wisegas.common.webservices.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import com.wisegas.shoppinglists.service.api.ShoppingListService;
import com.wisegas.shoppinglists.service.dto.ShoppingListDto;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Path("/shoppingLists/{id}/")
public class ShoppingListResource extends JaxrsHalJsonResource {

   private final ShoppingListService shoppingListService;

   @Inject
   public ShoppingListResource(ShoppingListService shoppingListService) {
      this.shoppingListService = shoppingListService;
   }

   @GET
   public Response get(@PathParam("id") String id) {
      return buildHalResponse(asRepresentationOf(shoppingListService.get(id)));
   }

   @POST
   @Path("addItem")
   public Response addItem(@PathParam("id") String id,
                           @QueryParam("item") CodeName item) {
      return buildHalResponse(ShoppingListItemResource.asRepresentationOf(shoppingListService.addItem(id, item)));
   }

   @DELETE
   public Response delete(@PathParam("id") String id) {
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
      return Arrays.asList(
         createSelfLinkBuilder().pathArgs(shoppingListDto.getId()).withSelfRel(),
         createSelfLinkBuilder().pathArgs(shoppingListDto.getId()).method("addItem").queryParams("item").withRel("addItem")
      );
   }

   private static JaxrsHalResourceLinkBuilder createSelfLinkBuilder() {
      return JaxrsHalResourceLinkBuilder.linkTo(ShoppingListResource.class);
   }
}
