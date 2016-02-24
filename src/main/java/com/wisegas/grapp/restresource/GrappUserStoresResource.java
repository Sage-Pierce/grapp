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

@Path("/users/{ownerID}/stores/")
public class GrappUserStoresResource extends HALResource {

   private final GrappStoreService grappStoreService;

   @Inject
   public GrappUserStoresResource(GrappStoreService grappStoreService) {
      this.grappStoreService = grappStoreService;
   }

   @POST
   public Response create(@PathParam(value = "ownerID") final String ownerID,
                          @QueryParam(value = "storeName") final String storeName,
                          @QueryParam(value = "storeLocation") final GeoPoint storeLocation) {
      GrappStoreDTO grappStoreDTO = grappStoreService.createForOwner(ownerID, storeName, storeLocation);
      HALRepresentation halRepresentation = GrappStoreResource.asRepresentation(grappStoreDTO);
      return buildHALResponse(halRepresentation);
   }

   @GET
   public Response getAllForOwner(@PathParam(value = "ownerID") final String ownerID) {
      List<GrappStoreDTO> grappStoreDTOs = grappStoreService.findAllForOwner(ownerID);
      HALRepresentation halRepresentation = halRepresentationFactory.createEmpty().withEmbeddeds("stores", GrappStoreResource.asRepresentations(grappStoreDTOs));
      return buildHALResponse(halRepresentation);
   }
}
