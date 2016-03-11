package com.wisegas.grapp.restresource;

import com.wisegas.grapp.service.api.GrappStoreService;
import com.wisegas.grapp.service.dto.GrappStoreDTO;
import com.wisegas.common.lang.value.GeoPoint;
import com.wisegas.common.webserver.hal.HALResource;
import com.wisegas.common.webserver.hal.HALResourceLinkBuilder;
import com.wisegas.common.webserver.hal.api.HALLink;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Path("/stores/")
public class GrappStoresResource extends HALResource {

   private final GrappStoreService grappStoreService;

   @Inject
   public GrappStoresResource(GrappStoreService grappStoreService) {
      this.grappStoreService = grappStoreService;
   }

   @POST
   public Response createStore(@QueryParam(value = "storeName") final String storeName,
                               @QueryParam(value = "storeLocation") final GeoPoint storeLocation) {
      GrappStoreDTO grappStoreDTO = grappStoreService.create(storeName, storeLocation);
      return buildHALResponse(GrappStoreResource.asRepresentationOf(grappStoreDTO));
   }

   @GET
   public Response findAll() {
      List<GrappStoreDTO> grappStoreDTOs = grappStoreService.findAll();
      return buildHALResponse(halRepresentationFactory.createForLinks(createLinks()).withEmbeddeds("stores", GrappStoreResource.asRepresentations(grappStoreDTOs)));
   }

   private static List<HALLink> createLinks() {
      return Collections.singletonList(
         HALResourceLinkBuilder.linkTo(GrappStoresResource.class).withSelfRel()
      );
   }
}
