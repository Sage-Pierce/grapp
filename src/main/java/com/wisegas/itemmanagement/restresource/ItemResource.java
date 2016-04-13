package com.wisegas.itemmanagement.restresource;

import com.wisegas.common.webserver.hal.api.HalLink;
import com.wisegas.common.webserver.hal.api.HalRepresentation;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalJsonResource;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import com.wisegas.itemmanagement.service.api.ItemService;
import com.wisegas.itemmanagement.service.dto.ItemDto;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Path("/items/{id}/")
public class ItemResource extends JaxrsHalJsonResource {

   private final ItemService itemService;

   @Inject
   public ItemResource(ItemService itemService) {
      this.itemService = itemService;
   }

   @GET
   public Response get(@PathParam("id") final String id) {
      return buildHalResponse(asRepresentationOf(itemService.get(id)));
   }

   @DELETE
   public Response delete(@PathParam("id") final String id) {
      itemService.delete(id);
      return Response.ok().build();
   }

   public static HalLink createRootLink(String rel) {
      return createSelfLinkBuilder().withRel(rel);
   }

   protected static HalRepresentation asRepresentationOf(ItemDto itemDto) {
      return halRepresentationFactory.createFor(itemDto).withLinks(createLinks(itemDto));
   }

   private static List<HalLink> createLinks(ItemDto itemDto) {
      return Collections.singletonList(createSelfLinkBuilder().pathArgs(itemDto.getId()).withSelfRel());
   }

   private static JaxrsHalResourceLinkBuilder createSelfLinkBuilder() {
      return JaxrsHalResourceLinkBuilder.linkTo(ItemResource.class);
   }
}
