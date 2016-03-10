package com.wisegas.grapp.restresource;

import com.wisegas.grapp.service.api.GrappUserService;
import com.wisegas.grapp.service.dto.GrappUserDTO;
import com.wisegas.webserver.hal.HALResource;
import com.wisegas.webserver.hal.HALResourceLinkBuilder;
import com.wisegas.webserver.hal.api.HALLink;
import com.wisegas.webserver.hal.api.HALRepresentation;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

import static java.util.Arrays.asList;

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
   @Path("name")
   public Response updateName(@PathParam(value = "id") final String id,
                              @QueryParam(value = "name") final String name) {
      GrappUserDTO grappUserDTO = grappUserService.updateName(id, name);
      return buildHALResponse(asRepresentationOf(grappUserDTO));
   }

   protected static HALRepresentation asRepresentationOf(GrappUserDTO grappUserDTO) {
      return halRepresentationFactory.createFor(grappUserDTO).withLinks(createLinks(grappUserDTO));
   }

   private static List<HALLink> createLinks(GrappUserDTO grappUserDTO) {
      return asList(
         HALResourceLinkBuilder.linkTo(GrappUserResource.class).pathArgs(grappUserDTO.getId()).withSelfRel(),
         HALResourceLinkBuilder.linkTo(GrappUserResource.class).method("updateName").pathArgs(grappUserDTO.getId()).queryParams("name").withRel("updateName")
      );
   }
}
