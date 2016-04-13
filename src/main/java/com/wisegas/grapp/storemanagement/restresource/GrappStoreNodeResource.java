package com.wisegas.grapp.storemanagement.restresource;

import com.wisegas.common.webserver.hal.api.HalLink;
import com.wisegas.common.webserver.hal.api.HalRepresentation;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalJsonResource;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import com.wisegas.grapp.storemanagement.service.api.GrappStoreNodeService;
import com.wisegas.grapp.storemanagement.service.dto.GrappStoreNodeDTOO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Path("/nodes/{id}/")
public class GrappStoreNodeResource extends JaxrsHalJsonResource {

   private final GrappStoreNodeService grappStoreNodeService;

   @Inject
   public GrappStoreNodeResource(GrappStoreNodeService grappStoreNodeService) {
      this.grappStoreNodeService = grappStoreNodeService;
   }

   @GET
   public Response get(@PathParam("id") final String id) {
      return buildHalResponse(asRepresentationOf(grappStoreNodeService.get(id)));
   }

   @PUT
   public Response update(@PathParam("id") final String id,
                          @QueryParam("name") final String name) {
      GrappStoreNodeDTOO grappStoreNodeDTO = grappStoreNodeService.update(id, name);
      return buildHalResponse(asRepresentationOf(grappStoreNodeDTO));
   }

   public static HalLink createRootLink(String rel) {
      return createSelfLinkBuilder().withRel(rel);
   }

   protected static HalRepresentation asRepresentationOf(GrappStoreNodeDTOO grappStoreNodeDTO) {
      return halRepresentationFactory.createFor(grappStoreNodeDTO).withLinks(createLinks(grappStoreNodeDTO));
   }

   private static List<HalLink> createLinks(GrappStoreNodeDTOO grappStoreNodeDTO) {
      return Collections.singletonList(createSelfLinkBuilder().pathArgs(grappStoreNodeDTO.getId()).withSelfRel());
   }

   private static JaxrsHalResourceLinkBuilder createSelfLinkBuilder() {
      return JaxrsHalResourceLinkBuilder.linkTo(GrappStoreNodeResource.class).queryParams("name");
   }
}
