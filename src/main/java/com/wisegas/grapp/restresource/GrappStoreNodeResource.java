package com.wisegas.grapp.restresource;

import com.wisegas.common.webserver.hal.api.HalLink;
import com.wisegas.common.webserver.hal.api.HalRepresentation;
import com.wisegas.common.webserver.jersey.hal.JerseyHalJsonResource;
import com.wisegas.common.webserver.jersey.hal.JerseyHalResourceLinkBuilder;
import com.wisegas.grapp.service.api.GrappStoreNodeService;
import com.wisegas.grapp.service.dto.GrappStoreNodeDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Path("/nodes/{id}/")
public class GrappStoreNodeResource extends JerseyHalJsonResource {

   private final GrappStoreNodeService grappStoreNodeService;

   @Inject
   public GrappStoreNodeResource(GrappStoreNodeService grappStoreNodeService) {
      this.grappStoreNodeService = grappStoreNodeService;
   }

   @GET
   public Response get(@PathParam("id") final String id) {
      GrappStoreNodeDTO grappStoreNodeDTO = grappStoreNodeService.get(id);
      return buildHalResponse(asRepresentationOf(grappStoreNodeDTO));
   }

   @PUT
   public Response update(@PathParam("id") final String id,
                          @QueryParam("name") final String name) {
      GrappStoreNodeDTO grappStoreNodeDTO = grappStoreNodeService.update(id, name);
      return buildHalResponse(asRepresentationOf(grappStoreNodeDTO));
   }

   protected static HalRepresentation asRepresentationOf(GrappStoreNodeDTO grappStoreNodeDTO) {
      return halRepresentationFactory.createFor(grappStoreNodeDTO).withLinks(createLinks(grappStoreNodeDTO));
   }

   protected static HalLink createRootLink(String rel) {
      return createSelfLinkBuilder().withRel(rel);
   }

   private static List<HalLink> createLinks(GrappStoreNodeDTO grappStoreNodeDTO) {
      return Collections.singletonList(createSelfLinkBuilder().pathArgs(grappStoreNodeDTO.getId()).withSelfRel());
   }

   private static JerseyHalResourceLinkBuilder createSelfLinkBuilder() {
      return JerseyHalResourceLinkBuilder.linkTo(GrappStoreNodeResource.class).queryParams("name");
   }
}
