package com.wisegas.grapp.restresource;

import com.wisegas.grapp.service.api.GrappLoginService;
import com.wisegas.grapp.service.dto.GrappUserDTO;
import com.wisegas.webserver.hal.HALResource;
import com.wisegas.webserver.hal.api.HALRepresentation;

import javax.inject.Inject;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/login/")
public class GrappLoginResource extends HALResource {

   private final GrappLoginService grappLoginService;

   @Inject
   public GrappLoginResource(GrappLoginService grappLoginService) {
      this.grappLoginService = grappLoginService;
   }

   @PUT
   @Path("logIn")
   public Response logIn(@QueryParam(value = "email") final String email,
                         @QueryParam(value = "avatar") final String avatar) {
      GrappUserDTO grappUserDTO = grappLoginService.logIn(email, avatar);
      HALRepresentation halRepresentation = halRepresentationFactory.createFor(grappUserDTO).withLinks(GrappUserResource.createLinks(grappUserDTO));
      return buildHALResponse(halRepresentation);
   }
}
