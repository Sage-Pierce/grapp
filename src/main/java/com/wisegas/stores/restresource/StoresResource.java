package com.wisegas.stores.restresource;

import com.wisegas.common.webservices.hal.api.HalLink;
import com.wisegas.common.webservices.jaxrs.hal.JaxrsHalJsonResource;
import com.wisegas.common.webservices.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import com.wisegas.stores.service.api.StoreService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Path("/stores/")
public class StoresResource extends JaxrsHalJsonResource {

   private final StoreService storeService;

   @Inject
   public StoresResource(StoreService storeService) {
      this.storeService = storeService;
   }

   @GET
   public Response get() {
      return buildHalResponse(halRepresentationFactory.createFor(storeService.getAll()).withLinks(createLinks()));
   }

   public static HalLink createRootLink(String rel) {
      return createSelfLinkBuilder().withRel(rel);
   }

   private static List<HalLink> createLinks() {
      return Collections.singletonList(createSelfLinkBuilder().withSelfRel());
   }

   private static JaxrsHalResourceLinkBuilder createSelfLinkBuilder() {
      return JaxrsHalResourceLinkBuilder.linkTo(StoresResource.class);
   }
}
