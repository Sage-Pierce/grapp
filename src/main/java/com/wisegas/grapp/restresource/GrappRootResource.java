package com.wisegas.grapp.restresource;

import com.wisegas.common.webserver.hal.api.HALLink;
import com.wisegas.common.webserver.jersey.hal.JerseyHalResource;
import com.wisegas.common.webserver.jersey.hal.JerseyHalResourceLinkBuilder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Path("/")
public class GrappRootResource extends JerseyHalResource {

   @GET
   public Response getRoot() {
      return buildHalResponse(halRepresentationFactory.createForLinks(createLinks()));
   }

   private static List<HALLink> createLinks() {
      return Arrays.asList(
         JerseyHalResourceLinkBuilder.linkTo(GrappRootResource.class).withSelfRel(),
         GrappLoginResource.createRootLink("logIn"),
         GrappUserResource.createRootLink("userById"),
         GrappStoreLayoutResource.createRootLink("storeLayoutById"),
         GrappStoreNodeResource.createRootLink("nodeById"),
         GrappStoreResource.createRootLink("storeById"),
         GrappStoresResource.createRootLink("stores"),
         GrappGeneralItemsResource.createRootLink("generalItems"),
         GrappItemsResource.createRootLink("items"),
         GrappItemResource.createRootLink("itemById")
      );
   }
}
