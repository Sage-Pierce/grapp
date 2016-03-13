package com.wisegas.grapp.restresource;

import com.wisegas.grapp.service.api.GrappStoreNodeService;
import com.wisegas.grapp.service.dto.GrappStoreNodeDTO;
import com.wisegas.common.webserver.hal.HALResource;
import com.wisegas.common.webserver.hal.HALResourceLinkBuilder;
import com.wisegas.common.webserver.hal.api.HALLink;
import com.wisegas.common.webserver.hal.api.HALRepresentation;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Path("/nodes/{id}/")
public class GrappStoreNodeResource extends HALResource {

   private final GrappStoreNodeService grappStoreNodeService;

   @Inject
   public GrappStoreNodeResource(GrappStoreNodeService grappStoreNodeService) {
      this.grappStoreNodeService = grappStoreNodeService;
   }

   @GET
   public Response findByID(@PathParam("id") final String id) {
      GrappStoreNodeDTO grappStoreNodeDTO = grappStoreNodeService.findByID(id);
      return buildHALResponse(asRepresentationOf(grappStoreNodeDTO));
   }

   @PUT
   public Response updateByID(@PathParam("id") final String id,
                              @QueryParam("name") final String name) {
      GrappStoreNodeDTO grappStoreNodeDTO = grappStoreNodeService.updateByID(id, name);
      return buildHALResponse(asRepresentationOf(grappStoreNodeDTO));
   }

   protected static HALRepresentation asRepresentationOf(GrappStoreNodeDTO grappStoreNodeDTO) {
      return halRepresentationFactory.createFor(grappStoreNodeDTO).withLinks(createLinks(grappStoreNodeDTO));
   }

   private static List<HALLink> createLinks(GrappStoreNodeDTO grappStoreNodeDTO) {
      return Arrays.asList(
         HALResourceLinkBuilder.linkTo(GrappStoreNodeResource.class).pathArgs(grappStoreNodeDTO.getId()).withSelfRel(),
         HALResourceLinkBuilder.linkTo(GrappStoreNodeResource.class).pathArgs(grappStoreNodeDTO.getId()).queryParams("name").withRel("updateByID")
      );
   }
}
