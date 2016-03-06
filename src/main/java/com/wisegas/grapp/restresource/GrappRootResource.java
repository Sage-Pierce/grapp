package com.wisegas.grapp.restresource;

import com.wisegas.webserver.hal.HALResource;
import com.wisegas.webserver.hal.HALResourceLinkBuilder;
import com.wisegas.webserver.hal.api.HALLink;
import com.wisegas.webserver.hal.api.HALRepresentation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Path("/")
public class GrappRootResource extends HALResource {

   @GET
   public Response getRoot() {
      HALRepresentation halRepresentation = halRepresentationFactory.createEmpty().withLinks(createLinks());
      return buildHALResponse(halRepresentation);
   }

   protected static List<HALLink> createLinks() {
      return Arrays.asList(
         HALResourceLinkBuilder.linkTo(GrappRootResource.class).withSelfRel(),
         HALResourceLinkBuilder.linkTo(GrappLoginResource.class).method("logIn").queryParams("email", "avatar").withRel("logIn"),
         HALResourceLinkBuilder.linkTo(GrappUserResource.class).withRel("userByID"),
         HALResourceLinkBuilder.linkTo(GrappStoresResource.class).queryParams("storeName", "storeLocation").withRel("createStore"),
         HALResourceLinkBuilder.linkTo(GrappStoresResource.class).withRel("stores"),
         HALResourceLinkBuilder.linkTo(GrappStoreResource.class).withRel("storeByID"),
         HALResourceLinkBuilder.linkTo(GrappStoreLayoutResource.class).withRel("storeLayoutByID")
      );
   }
}
