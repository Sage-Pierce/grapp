package com.wisegas.grapp.user.restresource;

import com.wisegas.common.webserver.hal.api.HalLink;
import com.wisegas.common.webserver.hal.api.HalRepresentation;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalJsonResource;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import com.wisegas.grapp.user.service.api.UserService;
import com.wisegas.grapp.user.service.dto.UserDto;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Path("/users/{id}/")
public class UserResource extends JaxrsHalJsonResource {

   private final UserService userService;

   @Inject
   public UserResource(UserService userService) {
      this.userService = userService;
   }

   @GET
   public Response get(@PathParam(value = "id") final String id) {
      UserDto userDto = userService.get(id);
      return buildHalResponse(asRepresentationOf(userDto));
   }

   @PUT
   public Response update(@PathParam(value = "id") final String id,
                          @QueryParam(value = "name") final String name) {
      UserDto userDto = userService.update(id, name);
      return buildHalResponse(asRepresentationOf(userDto));
   }

   public static HalLink createRootLink(String rel) {
      return createSelfLinkBuilder().withRel(rel);
   }

   protected static HalRepresentation asRepresentationOf(UserDto userDto) {
      return halRepresentationFactory.createFor(userDto).withLinks(createLinks(userDto));
   }

   private static List<HalLink> createLinks(UserDto userDto) {
      return Collections.singletonList(createSelfLinkBuilder().pathArgs(userDto.getId()).withSelfRel());
   }

   private static JaxrsHalResourceLinkBuilder createSelfLinkBuilder() {
      return JaxrsHalResourceLinkBuilder.linkTo(UserResource.class).queryParams("name");
   }
}
