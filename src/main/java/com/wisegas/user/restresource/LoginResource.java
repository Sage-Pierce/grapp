package com.wisegas.user.restresource;

import com.wisegas.common.webserver.hal.api.HalLink;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalJsonResource;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import com.wisegas.user.service.api.LoginService;
import com.wisegas.user.service.dto.UserDto;

import javax.inject.Inject;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/login/")
public class LoginResource extends JaxrsHalJsonResource {

   private final LoginService loginService;

   @Inject
   public LoginResource(LoginService loginService) {
      this.loginService = loginService;
   }

   @PUT
   public Response logIn(@QueryParam(value = "email") final String email,
                         @QueryParam(value = "avatar") final String avatar) {
      UserDto userDto = loginService.logIn(email, avatar);
      return buildHalResponse(UserResource.asRepresentationOf(userDto));
   }

   public static HalLink createRootLink(String rel) {
      return JaxrsHalResourceLinkBuilder.linkTo(LoginResource.class).queryParams("email", "avatar").withRel(rel);
   }
}
