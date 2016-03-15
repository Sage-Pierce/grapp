package com.wisegas.grapp.restresource;

import com.wisegas.common.webserver.hal.HALResource;
import com.wisegas.common.webserver.hal.HALResourceLinkBuilder;
import com.wisegas.common.webserver.hal.api.HALLink;
import com.wisegas.common.webserver.hal.api.HALRepresentation;
import com.wisegas.grapp.service.api.GrappStoreNodeService;
import com.wisegas.grapp.service.dto.GrappStoreNodeDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Path("/nodes/{id}/")
public class GrappStoreNodeResource extends HALResource {

   private final GrappStoreNodeService grappStoreNodeService;

   @Inject
   public GrappStoreNodeResource(GrappStoreNodeService grappStoreNodeService) {
      this.grappStoreNodeService = grappStoreNodeService;
   }

   @GET
   public Response get(@PathParam("id") final String id) {
      GrappStoreNodeDTO grappStoreNodeDTO = grappStoreNodeService.get(id);
      return buildHALResponse(asRepresentationOf(grappStoreNodeDTO));
   }

   @PUT
   public Response update(@PathParam("id") final String id,
                          @QueryParam("name") final String name) {
      GrappStoreNodeDTO grappStoreNodeDTO = grappStoreNodeService.update(id, name);
      return buildHALResponse(asRepresentationOf(grappStoreNodeDTO));
   }

   protected static HALRepresentation asRepresentationOf(GrappStoreNodeDTO grappStoreNodeDTO) {
      return halRepresentationFactory.createFor(grappStoreNodeDTO).withLinks(createLinks(grappStoreNodeDTO));
   }

   protected static HALLink createRootLink(String rel) {
      return createSelfLinkBuilder().withRel(rel);
   }

   private static List<HALLink> createLinks(GrappStoreNodeDTO grappStoreNodeDTO) {
      return Collections.singletonList(createSelfLinkBuilder().pathArgs(grappStoreNodeDTO.getId()).withSelfRel());
   }

   private static HALResourceLinkBuilder createSelfLinkBuilder() {
      return HALResourceLinkBuilder.linkTo(GrappStoreNodeResource.class).queryParams("name");
   }
}
