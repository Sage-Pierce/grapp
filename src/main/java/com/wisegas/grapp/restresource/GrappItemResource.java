package com.wisegas.grapp.restresource;

import com.wisegas.common.webserver.hal.HALResource;
import com.wisegas.common.webserver.hal.HALResourceLinkBuilder;
import com.wisegas.common.webserver.hal.api.HALLink;
import com.wisegas.common.webserver.hal.api.HALRepresentation;
import com.wisegas.grapp.service.api.GrappItemService;
import com.wisegas.grapp.service.dto.GrappItemDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Path("/items/{id}/")
public class GrappItemResource extends HALResource {

   private final GrappItemService grappItemService;

   @Inject
   public GrappItemResource(GrappItemService grappItemService) {
      this.grappItemService = grappItemService;
   }

   @GET
   public Response get(@PathParam("id") final String id) {
      return buildHALResponse(asRepresentationOf(grappItemService.get(id)));
   }

   @PUT
   public Response update(@PathParam("id") final String id,
                          @QueryParam("name") final String name) {
      return buildHALResponse(asRepresentationOf(grappItemService.update(id, name)));
   }

   @DELETE
   public Response delete(@PathParam("id") final String id) {
      grappItemService.delete(id);
      return Response.ok().build();
   }

   protected static HALRepresentation asRepresentationOf(GrappItemDTO grappItemDTO) {
      return halRepresentationFactory.createFor(grappItemDTO).withLinks(createLinks(grappItemDTO));
   }

   protected static HALLink createRootLink(String rel) {
      return createSelfLinkBuilder().withRel(rel);
   }

   private static List<HALLink> createLinks(GrappItemDTO grappItemDTO) {
      return Collections.singletonList(createSelfLinkBuilder().pathArgs(grappItemDTO.getId()).withSelfRel());
   }

   private static HALResourceLinkBuilder createSelfLinkBuilder() {
      return HALResourceLinkBuilder.linkTo(GrappItemResource.class).queryParams("name");
   }
}
