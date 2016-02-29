package com.wisegas.grapp.restresource;

import com.wisegas.grapp.service.api.GrappStoreNodeService;
import com.wisegas.grapp.service.dto.GrappStoreNodeDTO;
import com.wisegas.webserver.hal.HALResource;
import com.wisegas.webserver.hal.HALResourceLinkBuilder;
import com.wisegas.webserver.hal.api.HALLink;
import com.wisegas.webserver.hal.api.HALRepresentation;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
   public Response findByID(@PathParam("id") final String id) {
      GrappStoreNodeDTO grappStoreNodeDTO = grappStoreNodeService.findByID(id);
      HALRepresentation halRepresentation = halRepresentationFactory.createFor(grappStoreNodeDTO).withLinks(createLinks(grappStoreNodeDTO));
      return buildHALResponse(halRepresentation);
   }

   protected static List<HALLink> createLinks(GrappStoreNodeDTO grappStoreNodeDTO) {
      return Collections.singletonList(
         HALResourceLinkBuilder.linkTo(GrappStoreNodeResource.class).pathArgs(grappStoreNodeDTO.getId()).withSelfRel()
      );
   }
}
