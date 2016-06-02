package com.wisegas.stores.restresource;

import com.wisegas.common.webservices.hal.api.HalLink;
import com.wisegas.common.webservices.jaxrs.hal.JaxrsHalJsonResource;
import com.wisegas.common.webservices.jaxrs.hal.JaxrsHalResourceLinkBuilder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Path("/stores/root/")
public class RootResource extends JaxrsHalJsonResource {

   @GET
   public Response getRoot() {
      return buildHalResponse(halRepresentationFactory.createForLinks(createLinks()));
   }

   private static List<HalLink> createLinks() {
      return Arrays.asList(
         JaxrsHalResourceLinkBuilder.linkTo(RootResource.class).withSelfRel(),
         StoreManagersResource.createRootLink("storeManagers"),
         StoreManagerResource.createRootLink("storeManager"),
         StoresResource.createRootLink("stores"),
         StoreResource.createRootLink("store"),
         StoreLayoutResource.createRootLink("storeLayout"),
         FeatureResource.createRootLink("feature"),
         NodeResource.createRootLink("node"),
         NodeItemResource.createRootLink("nodeItem"),
         ShoppingLayoutResource.createRootLink("shoppingLayout")
      );
   }
}
