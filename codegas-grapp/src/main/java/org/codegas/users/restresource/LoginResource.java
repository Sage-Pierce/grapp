package org.codegas.users.restresource;

import javax.inject.Inject;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.codegas.commons.lang.value.Email;
import org.codegas.webservices.hal.api.HalLink;
import org.codegas.webservices.hal.jaxrs.HalJsonResource;
import org.codegas.webservices.hal.jaxrs.HalResourceLinkBuilder;
import org.codegas.users.service.api.LoginService;

@Path("/login/")
public class LoginResource extends HalJsonResource {

    private final LoginService loginService;

    @Inject
    public LoginResource(LoginService loginService) {
        this.loginService = loginService;
    }

    @PUT
    public Response logIn(@QueryParam(value = "email") Email email,
        @QueryParam(value = "avatar") String avatar) {
        return buildHalResponse(UserResource.asRepresentationOf(loginService.logIn(email, avatar)));
    }

    public static HalLink createRootLink(String rel) {
        return HalResourceLinkBuilder.linkTo(LoginResource.class).queryParams("email", "avatar").withRel(rel);
    }
}
