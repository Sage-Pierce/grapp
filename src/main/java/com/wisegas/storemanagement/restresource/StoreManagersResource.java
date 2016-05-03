package com.wisegas.storemanagement.restresource;

import com.wisegas.common.lang.value.Email;
import com.wisegas.common.webservices.hal.api.HalLink;
import com.wisegas.common.webservices.jaxrs.hal.JaxrsHalJsonResource;
import com.wisegas.common.webservices.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import com.wisegas.storemanagement.service.api.StoreManagerService;

import javax.inject.Inject;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/storeManagers/")
public class StoreManagersResource extends JaxrsHalJsonResource {

   private final StoreManagerService storeManagerService;

   @Inject
   public StoreManagersResource(StoreManagerService storeManagerService) {
      this.storeManagerService = storeManagerService;
   }

   @PUT
   public Response loadByEmail(@QueryParam("email") final Email email) {
      return buildHalResponse(StoreManagerResource.asRepresentationOf(storeManagerService.loadByEmail(email)));
   }

   public static HalLink createRootLink(String rel) {
      return createSelfLinkBuilder().withRel(rel);
   }

   private static JaxrsHalResourceLinkBuilder createSelfLinkBuilder() {
      return JaxrsHalResourceLinkBuilder.linkTo(StoreManagersResource.class).queryParams("email");
   }
}
