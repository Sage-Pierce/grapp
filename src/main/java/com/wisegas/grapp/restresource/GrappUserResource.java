package com.wisegas.grapp.restresource;

import com.wisegas.common.webserver.hal.HALResource;
import com.wisegas.common.webserver.hal.HALResourceLinkBuilder;
import com.wisegas.common.webserver.hal.api.HALLink;
import com.wisegas.common.webserver.hal.api.HALRepresentation;
import com.wisegas.grapp.service.api.GrappUserService;
import com.wisegas.grapp.service.dto.GrappUserDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Path("/users/{id}/")
public class GrappUserResource extends HALResource {

   private final GrappUserService grappUserService;

   @Inject
   public GrappUserResource(GrappUserService grappUserService) {
      this.grappUserService = grappUserService;
   }

   @GET
   public Response findByID(@PathParam(value = "id") final String id) {
      GrappUserDTO grappUserDTO = grappUserService.findByID(id);
      return buildHALResponse(asRepresentationOf(grappUserDTO));
   }

   @PUT
   public Response updateByID(@PathParam(value = "id") final String id,
                              @QueryParam(value = "name") final String name) {
      GrappUserDTO grappUserDTO = grappUserService.updateByID(id, name);
      return buildHALResponse(asRepresentationOf(grappUserDTO));
   }

   protected static HALRepresentation asRepresentationOf(GrappUserDTO grappUserDTO) {
      return halRepresentationFactory.createFor(grappUserDTO).withLinks(createLinks(grappUserDTO));
   }

   protected static HALLink createRootLink(String rel) {
      return createSelfLinkBuilder().withRel(rel);
   }

   private static List<HALLink> createLinks(GrappUserDTO grappUserDTO) {
      return Collections.singletonList(createSelfLinkBuilder().pathArgs(grappUserDTO.getId()).withSelfRel());
   }

   private static HALResourceLinkBuilder createSelfLinkBuilder() {
      return HALResourceLinkBuilder.linkTo(GrappUserResource.class).queryParams("name");
   }
}
