package com.wisegas.grapp.restresource;

import com.wisegas.common.webserver.hal.api.HalLink;
import com.wisegas.common.webserver.jersey.hal.JerseyHalJsonResource;
import com.wisegas.common.webserver.jersey.hal.JerseyHalResourceLinkBuilder;
import com.wisegas.grapp.service.api.GrappLoginService;
import com.wisegas.grapp.service.dto.GrappUserDTO;

import javax.inject.Inject;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/login/")
public class GrappLoginResource extends JerseyHalJsonResource {

   private final GrappLoginService grappLoginService;

   @Inject
   public GrappLoginResource(GrappLoginService grappLoginService) {
      this.grappLoginService = grappLoginService;
   }

   @PUT
   public Response logIn(@QueryParam(value = "email") final String email,
                         @QueryParam(value = "avatar") final String avatar) {
      GrappUserDTO grappUserDTO = grappLoginService.logIn(email, avatar);
      return buildHalResponse(GrappUserResource.asRepresentationOf(grappUserDTO));
   }

   protected static HalLink createRootLink(String rel) {
      return JerseyHalResourceLinkBuilder.linkTo(GrappLoginResource.class).queryParams("email", "avatar").withRel(rel);
   }
}
