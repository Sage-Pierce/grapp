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
      GrappUserDto grappUserDto = grappUserService.get(id);
      return buildHalResponse(asRepresentationOf(grappUserDto));
   }

   @PUT
   public Response update(@PathParam(value = "id") final String id,
                          @QueryParam(value = "name") final String name) {
      GrappUserDto grappUserDto = grappUserService.update(id, name);
      return buildHalResponse(asRepresentationOf(grappUserDto));
   }

   public static HalLink createRootLink(String rel) {
      return createSelfLinkBuilder().withRel(rel);
   }

   protected static HalRepresentation asRepresentationOf(GrappUserDto grappUserDto) {
      return halRepresentationFactory.createFor(grappUserDto).withLinks(createLinks(grappUserDto));
   }

   private static List<HalLink> createLinks(GrappUserDto grappUserDto) {
      return Collections.singletonList(createSelfLinkBuilder().pathArgs(grappUserDto.getId()).withSelfRel());
   }

   private static JaxrsHalResourceLinkBuilder createSelfLinkBuilder() {
      return JaxrsHalResourceLinkBuilder.linkTo(GrappUserResource.class).queryParams("name");
   }
}
