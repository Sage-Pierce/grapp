package com.wisegas.application.restresource;

import com.wisegas.common.webserver.hal.api.HalLink;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalJsonResource;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import com.wisegas.itemmanagement.restresource.GeneralItemsResource;
import com.wisegas.itemmanagement.restresource.ItemImportResource;
import com.wisegas.itemmanagement.restresource.ItemResource;
import com.wisegas.itemmanagement.restresource.ItemsResource;
import com.wisegas.storemanagement.restresource.*;
import com.wisegas.user.restresource.LoginResource;
import com.wisegas.user.restresource.UserResource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Path("/")
public class RootResource extends JaxrsHalJsonResource {

   @GET
   public Response getRoot() {
      return buildHalResponse(halRepresentationFactory.createForLinks(createLinks()));
   }

   private static List<HalLink> createLinks() {
      return Arrays.asList(
         JaxrsHalResourceLinkBuilder.linkTo(RootResource.class).withSelfRel(),
         LoginResource.createRootLink("logIn"),
         UserResource.createRootLink("userById"),
         StoreManagersResource.createRootLink("storeManagers"),
         StoreManagerResource.createRootLink("storeManagerById"),
         StoreResource.createRootLink("storeById"),
         LayoutResource.createRootLink("layoutById"),
         NodeResource.createRootLink("nodeById"),
         NodeItemResource.createRootLink("nodeItemById"),
         GeneralItemsResource.createRootLink("generalItems"),
         ItemsResource.createRootLink("items"),
         ItemImportResource.createRootLink("importItems"),
         ItemResource.createRootLink("itemById")
      );
   }
}
