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
         GrappLoginResource.createRootLink("logIn"),
         GrappUserResource.createRootLink("userById"),
         GrappStoreLayoutResource.createRootLink("storeLayoutById"),
         GrappStoreNodeResource.createRootLink("nodeById"),
         GrappStoreResource.createRootLink("storeById"),
         GrappStoresResource.createRootLink("stores"),
         GrappGeneralItemsResource.createRootLink("generalItems"),
         GrappSubItemsResource.createRootLink("subItems"),
         GrappItemResource.createRootLink("itemById")
      );
   }
}
