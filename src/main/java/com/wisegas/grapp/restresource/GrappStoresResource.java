package com.wisegas.grapp.restresource;

import com.wisegas.grapp.service.api.GrappStoreService;
import com.wisegas.grapp.service.dto.GrappStoreDTO;
import com.wisegas.lang.GeoPoint;
import com.wisegas.webserver.hal.HALResource;
import com.wisegas.webserver.hal.api.HALRepresentation;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
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
      HALRepresentation halRepresentation = GrappStoreResource.asRepresentation(grappStoreDTO);
      return buildHALResponse(halRepresentation);
   }

   @GET
   public Response findAll() {
      List<GrappStoreDTO> grappStoreDTOs = grappStoreService.findAll();
      HALRepresentation halRepresentation = halRepresentationFactory.createEmpty().withEmbeddeds("stores", GrappStoreResource.asRepresentations(grappStoreDTOs));
      return buildHALResponse(halRepresentation);
   }
}
