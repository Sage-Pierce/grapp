package com.wisegas.grapp.restresource;

import com.wisegas.common.webserver.hal.api.HalLink;
import com.wisegas.common.webserver.hal.api.HalRepresentation;
import com.wisegas.common.webserver.jersey.hal.JerseyHalResource;
import com.wisegas.common.webserver.jersey.hal.JerseyHalResourceLinkBuilder;
import com.wisegas.grapp.service.api.GrappUserService;
import com.wisegas.grapp.service.dto.GrappUserDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Path("/users/{id}/")
public class GrappUserResource extends JerseyHalResource {

   private final GrappUserService grappUserService;

   @Inject
   public GrappUserResource(GrappUserService grappUserService) {
      this.grappUserService = grappUserService;
   }

   @GET
   public Response get(@PathParam(value = "id") final String id) {
      GrappUserDTO grappUserDTO = grappUserService.get(id);
      return buildHalResponse(asRepresentationOf(grappUserDTO));
   }

   @PUT
   public Response update(@PathParam(value = "id") final String id,
                          @QueryParam(value = "name") final String name) {
      GrappUserDTO grappUserDTO = grappUserService.update(id, name);
      return buildHalResponse(asRepresentationOf(grappUserDTO));
   }

   protected static HalRepresentation asRepresentationOf(GrappUserDTO grappUserDTO) {
      return halRepresentationFactory.createFor(grappUserDTO).withLinks(createLinks(grappUserDTO));
   }

   protected static HalLink createRootLink(String rel) {
      return createSelfLinkBuilder().withRel(rel);
   }

   private static List<HalLink> createLinks(GrappUserDTO grappUserDTO) {
      return Collections.singletonList(createSelfLinkBuilder().pathArgs(grappUserDTO.getId()).withSelfRel());
   }

   private static JerseyHalResourceLinkBuilder createSelfLinkBuilder() {
      return JerseyHalResourceLinkBuilder.linkTo(GrappUserResource.class).queryParams("name");
   }
}
