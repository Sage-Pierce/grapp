package com.wisegas.grapp.usermanagement.restresource;

import com.wisegas.common.webserver.hal.api.HalLink;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalJsonResource;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import com.wisegas.grapp.usermanagement.service.api.GrappLoginService;
import com.wisegas.grapp.usermanagement.service.dto.GrappUserDto;

import javax.inject.Inject;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/login/")
public class GrappLoginResource extends JaxrsHalJsonResource {

   private final GrappLoginService grappLoginService;

   @Inject
   public GrappLoginResource(GrappLoginService grappLoginService) {
      this.grappLoginService = grappLoginService;
   }

   @PUT
   public Response logIn(@QueryParam(value = "email") final String email,
                         @QueryParam(value = "avatar") final String avatar) {
      GrappUserDto grappUserDto = grappLoginService.logIn(email, avatar);
      return buildHalResponse(GrappUserResource.asRepresentationOf(grappUserDto));
   }

   public static HalLink createRootLink(String rel) {
      return JaxrsHalResourceLinkBuilder.linkTo(GrappLoginResource.class).queryParams("email", "avatar").withRel(rel);
   }
}
