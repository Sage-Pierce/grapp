package com.wisegas.grapp.usermanagement.restresource;

import com.wisegas.common.webserver.hal.api.HalLink;
import com.wisegas.common.webserver.hal.api.HalRepresentation;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalJsonResource;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import com.wisegas.grapp.usermanagement.service.api.GrappUserService;
import com.wisegas.grapp.usermanagement.service.dto.GrappUserDto;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Path("/users/{id}/")
public class GrappUserResource extends JaxrsHalJsonResource {

   private final GrappUserService grappUserService;

   @Inject
   public GrappUserResource(GrappUserService grappUserService) {
      this.grappUserService = grappUserService;
   }

   @GET
   public Response get(@PathParam(value = "id") final String id) {
      GrappUserDto grappUserDTO = grappUserService.get(id);
      return buildHalResponse(asRepresentationOf(grappUserDTO));
   }

   @PUT
   public Response update(@PathParam(value = "id") final String id,
                          @QueryParam(value = "name") final String name) {
      GrappUserDto grappUserDTO = grappUserService.update(id, name);
      return buildHalResponse(asRepresentationOf(grappUserDTO));
   }

   public static HalLink createRootLink(String rel) {
      return createSelfLinkBuilder().withRel(rel);
   }

   protected static HalRepresentation asRepresentationOf(GrappUserDto grappUserDTO) {
      return halRepresentationFactory.createFor(grappUserDTO).withLinks(createLinks(grappUserDTO));
   }

   private static List<HalLink> createLinks(GrappUserDto grappUserDTO) {
      return Collections.singletonList(createSelfLinkBuilder().pathArgs(grappUserDTO.getId()).withSelfRel());
   }

   private static JaxrsHalResourceLinkBuilder createSelfLinkBuilder() {
      return JaxrsHalResourceLinkBuilder.linkTo(GrappUserResource.class).queryParams("name");
   }
}
