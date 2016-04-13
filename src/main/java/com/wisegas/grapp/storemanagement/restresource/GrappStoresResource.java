package com.wisegas.grapp.storemanagement.restresource;

import com.wisegas.common.lang.value.GeoPoint;
import com.wisegas.common.webserver.hal.api.HalLink;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalJsonResource;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import com.wisegas.grapp.storemanagement.service.api.GrappStoreService;
import com.wisegas.grapp.storemanagement.service.dto.GrappStoreDto;

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
public class GrappStoresResource extends JaxrsHalJsonResource {

   private final GrappStoreService grappStoreService;

   @Inject
   public GrappStoresResource(GrappStoreService grappStoreService) {
      this.grappStoreService = grappStoreService;
   }

   @POST
   public Response create(@QueryParam(value = "name") final String name,
                          @QueryParam(value = "location") final GeoPoint location) {
      return buildHalResponse(GrappStoreResource.asRepresentationOf(grappStoreService.create(name, location)));
   }

   @GET
   public Response get() {
      List<GrappStoreDto> grappStoreDtos = grappStoreService.getAll();
      return buildHalResponse(halRepresentationFactory.createForLinks(createLinks())
                                                      .withEmbeddeds("stores", grappStoreDtos.stream()
                                                                                             .map(GrappStoreResource::asRepresentationOf)
                                                                                             .collect(Collectors.toList())));
   }

   public static HalLink createRootLink(String rel) {
      return createSelfLinkBuilder().withRel(rel);
   }

   private static List<HalLink> createLinks() {
      return Collections.singletonList(createSelfLinkBuilder().withSelfRel());
   }

   private static JaxrsHalResourceLinkBuilder createSelfLinkBuilder() {
      return JaxrsHalResourceLinkBuilder.linkTo(GrappStoresResource.class).queryParams("name", "location");
   }
}
