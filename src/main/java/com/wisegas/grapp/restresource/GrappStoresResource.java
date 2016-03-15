package com.wisegas.grapp.restresource;

import com.wisegas.common.lang.value.GeoPoint;
import com.wisegas.common.webserver.hal.HALResource;
import com.wisegas.common.webserver.hal.HALResourceLinkBuilder;
import com.wisegas.common.webserver.hal.api.HALLink;
import com.wisegas.grapp.service.api.GrappStoreService;
import com.wisegas.grapp.service.dto.GrappStoreDTO;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Path("/stores/")
public class GrappStoresResource extends HALResource {

   private final GrappStoreService grappStoreService;

   @Inject
   public GrappStoresResource(GrappStoreService grappStoreService) {
      this.grappStoreService = grappStoreService;
   }

   @POST
   public Response create(@QueryParam(value = "name") final String name,
                          @QueryParam(value = "location") final GeoPoint location) {
      GrappStoreDTO grappStoreDTO = grappStoreService.create(name, location);
      return buildHALResponse(GrappStoreResource.asRepresentationOf(grappStoreDTO));
   }

   @GET
   public Response get() {
      List<GrappStoreDTO> grappStoreDTOs = grappStoreService.getAll();
      return buildHALResponse(halRepresentationFactory.createForLinks(createLinks())
                                                      .withEmbeddeds("stores", grappStoreDTOs.stream()
                                                                                             .map(GrappStoreResource::asRepresentationOf)
                                                                                             .collect(Collectors.toList())));
   }

   protected static HALLink createRootLink(String rel) {
      return createSelfLinkBuilder().withRel(rel);
   }

   private static List<HALLink> createLinks() {
      return Collections.singletonList(createSelfLinkBuilder().withSelfRel());
   }

   private static HALResourceLinkBuilder createSelfLinkBuilder() {
      return HALResourceLinkBuilder.linkTo(GrappStoresResource.class).queryParams("name", "location");
   }
}
