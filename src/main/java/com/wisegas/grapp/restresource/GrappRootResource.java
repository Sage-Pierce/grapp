package com.wisegas.grapp.restresource;

import com.wisegas.common.webserver.hal.api.HalLink;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalJsonResource;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import com.wisegas.grapp.itemmanagement.restresource.GrappGeneralItemsResource;
import com.wisegas.grapp.itemmanagement.restresource.GrappItemImportResource;
import com.wisegas.grapp.itemmanagement.restresource.GrappItemResource;
import com.wisegas.grapp.itemmanagement.restresource.GrappItemsResource;
import com.wisegas.grapp.usermanagement.restresource.GrappLoginResource;
import com.wisegas.grapp.usermanagement.restresource.GrappUserResource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Path("/")
public class GrappRootResource extends JaxrsHalJsonResource {

   @GET
   public Response getRoot() {
      return buildHalResponse(halRepresentationFactory.createForLinks(createLinks()));
   }

   private static List<HalLink> createLinks() {
      return Arrays.asList(
         JaxrsHalResourceLinkBuilder.linkTo(GrappRootResource.class).withSelfRel(),
         GrappLoginResource.createRootLink("logIn"),
         GrappUserResource.createRootLink("userById"),
         GrappStoreLayoutResource.createRootLink("storeLayoutById"),
         GrappStoreNodeResource.createRootLink("nodeById"),
         GrappStoreResource.createRootLink("storeById"),
         GrappStoresResource.createRootLink("stores"),
         GrappGeneralItemsResource.createRootLink("generalItems"),
         GrappItemsResource.createRootLink("items"),
         GrappItemImportResource.createRootLink("importItems"),
         GrappItemResource.createRootLink("itemById")
      );
   }
}
