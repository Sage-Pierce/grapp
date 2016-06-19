package com.wisegas.itemmanagement.restresource;

import com.wisegas.common.webservices.hal.api.HalLink;
import com.wisegas.common.webservices.hal.api.HalRepresentation;
import com.wisegas.common.webservices.jaxrs.hal.JaxrsHalJsonResource;
import com.wisegas.common.webservices.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import com.wisegas.itemmanagement.service.api.ItemService;
import com.wisegas.itemmanagement.service.dto.ItemDto;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Path("/items/{primaryCode}/")
public class ItemResource extends JaxrsHalJsonResource {

   private final ItemService itemService;

   @Inject
   public ItemResource(ItemService itemService) {
      this.itemService = itemService;
   }

   @GET
   public Response get(@PathParam("primaryCode") final String primaryCode) {
      return buildHalResponse(asRepresentationOf(itemService.get(primaryCode)));
   }

   @PUT
   public Response update(@PathParam("primaryCode") final String primaryCode,
                          @QueryParam("name") final String name) {
      return buildHalResponse(asRepresentationOf(itemService.update(primaryCode, name)));
   }

   @PUT
   @Path("makeGeneral")
   public Response makeGeneral(@PathParam("primaryCode") final String primaryCode) {
      return buildHalResponse(asRepresentationOf(itemService.makeGeneral(primaryCode)));
   }

   @PUT
   @Path("move")
   public Response move(@PathParam("primaryCode") final String primaryCode,
                        @QueryParam("superItemCode") final String superItemCode) {
      return buildHalResponse(asRepresentationOf(itemService.move(primaryCode, superItemCode)));
   }

   @DELETE
   public Response delete(@PathParam("primaryCode") final String primaryCode) {
      itemService.delete(primaryCode);
      return Response.ok().build();
   }

   public static HalLink createRootLink(String rel) {
      return createSelfLinkBuilder().withRel(rel);
   }

   protected static HalRepresentation asRepresentationOf(ItemDto itemDto) {
      return halRepresentationFactory.createFor(itemDto).withLinks(createLinks(itemDto));
   }

   private static List<HalLink> createLinks(ItemDto itemDto) {
      return Arrays.asList(
         createSelfLinkBuilder().pathArgs(itemDto.getPrimaryCode()).withSelfRel(),
         JaxrsHalResourceLinkBuilder.linkTo(ItemResource.class).method("makeGeneral").pathArgs(itemDto.getPrimaryCode()).withRel("makeGeneral"),
         JaxrsHalResourceLinkBuilder.linkTo(ItemResource.class).method("move").pathArgs(itemDto.getPrimaryCode()).queryParams("superItemCode").withRel("move")
      );
   }

   private static JaxrsHalResourceLinkBuilder createSelfLinkBuilder() {
      return JaxrsHalResourceLinkBuilder.linkTo(ItemResource.class).queryParams("name");
   }
}
