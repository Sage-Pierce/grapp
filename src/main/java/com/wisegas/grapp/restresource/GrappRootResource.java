package com.wisegas.grapp.restresource;

import com.wisegas.common.webserver.hal.HALResource;
import com.wisegas.common.webserver.hal.HALResourceLinkBuilder;
import com.wisegas.common.webserver.hal.api.HALLink;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Path("/")
public class GrappRootResource extends HALResource {

   @GET
   public Response getRoot() {
      return buildHALResponse(halRepresentationFactory.createForLinks(createLinks()));
   }

   private static List<HALLink> createLinks() {
      return Arrays.asList(
         HALResourceLinkBuilder.linkTo(GrappRootResource.class).withSelfRel(),
         HALResourceLinkBuilder.linkTo(GrappLoginResource.class).method("logIn").queryParams("email", "avatar").withRel("logIn"),
         HALResourceLinkBuilder.linkTo(GrappUserResource.class).withRel("userByID"),
         HALResourceLinkBuilder.linkTo(GrappStoreLayoutResource.class).withRel("storeLayoutByID"),
         HALResourceLinkBuilder.linkTo(GrappStoreResource.class).withRel("storeByID"),
         HALResourceLinkBuilder.linkTo(GrappStoresResource.class).queryParams("storeName", "storeLocation").withRel("createStore"),
         HALResourceLinkBuilder.linkTo(GrappStoresResource.class).withRel("stores")
      );
   }
}
