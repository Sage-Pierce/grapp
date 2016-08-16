package com.wisegas.users.restresource;

import com.wisegas.common.lang.value.Email;
import com.wisegas.common.webservices.hal.api.HalLink;
import com.wisegas.common.webservices.hal.api.HalRepresentation;
import com.wisegas.common.webservices.jaxrs.hal.JaxrsHalJsonResource;
import com.wisegas.common.webservices.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import com.wisegas.users.service.api.UserService;
import com.wisegas.users.service.dto.UserDto;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Path("/users/{email}/")
public class UserResource extends JaxrsHalJsonResource {

   private final UserService userService;

   @Inject
   public UserResource(UserService userService) {
      this.userService = userService;
   }

   @GET
   public Response get(@PathParam(value = "email") Email email) {
      return buildHalResponse(asRepresentationOf(userService.get(email)));
   }

   @PUT
   public Response update(@PathParam(value = "email") Email email,
                          @QueryParam(value = "name") String name) {
      return buildHalResponse(asRepresentationOf(userService.update(email, name)));
   }

   public static HalLink createRootLink(String rel) {
      return createSelfLinkBuilder().withRel(rel);
   }

   protected static HalRepresentation asRepresentationOf(UserDto userDto) {
      return halRepresentationFactory.createFor(userDto).withLinks(createLinks(userDto));
   }

   private static List<HalLink> createLinks(UserDto userDto) {
      return Collections.singletonList(createSelfLinkBuilder().pathArgs(userDto.getEmail()).withSelfRel());
   }

   private static JaxrsHalResourceLinkBuilder createSelfLinkBuilder() {
      return JaxrsHalResourceLinkBuilder.linkTo(UserResource.class).queryParams("name");
   }
}
