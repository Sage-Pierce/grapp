package com.wisegas.grapp.application.restresource;

import com.wisegas.common.webserver.hal.api.HalLink;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalJsonResource;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import com.wisegas.grapp.itemmanagement.restresource.GeneralItemsResource;
import com.wisegas.grapp.itemmanagement.restresource.ItemImportResource;
import com.wisegas.grapp.itemmanagement.restresource.ItemResource;
import com.wisegas.grapp.itemmanagement.restresource.ItemsResource;
import com.wisegas.grapp.storemanagement.restresource.LayoutResource;
import com.wisegas.grapp.storemanagement.restresource.NodeResource;
import com.wisegas.grapp.storemanagement.restresource.StoreResource;
import com.wisegas.grapp.storemanagement.restresource.StoresResource;
import com.wisegas.grapp.usermanagement.restresource.LoginResource;
import com.wisegas.grapp.usermanagement.restresource.UserResource;

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
         LoginResource.createRootLink("logIn"),
         UserResource.createRootLink("userById"),
         LayoutResource.createRootLink("storeLayoutById"),
         NodeResource.createRootLink("nodeById"),
         StoreResource.createRootLink("storeById"),
         StoresResource.createRootLink("stores"),
         GeneralItemsResource.createRootLink("generalItems"),
         ItemsResource.createRootLink("items"),
         ItemImportResource.createRootLink("importItems"),
         ItemResource.createRootLink("itemById")
      );
   }
}
