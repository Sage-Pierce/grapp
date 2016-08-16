package com.wisegas.stores.restresource;

import com.wisegas.common.lang.spacial.GeoPoint;
import com.wisegas.common.webservices.hal.api.HalLink;
import com.wisegas.common.webservices.hal.api.HalRepresentation;
import com.wisegas.common.webservices.jaxrs.hal.JaxrsHalJsonResource;
import com.wisegas.common.webservices.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import com.wisegas.stores.service.api.StoreService;
import com.wisegas.stores.service.dto.StoreDto;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Path("/stores/{id}/")
public class StoreResource extends JaxrsHalJsonResource {

   private final StoreService storeService;

   @Inject
   public StoreResource(StoreService storeService) {
      this.storeService = storeService;
   }

   @GET
   public Response get(@PathParam(value = "id") String id) {
      return buildHalResponse(asRepresentationOf(storeService.get(id)));
   }

   @PUT
   public Response update(@PathParam(value = "id") String id,
                          @QueryParam(value = "name") String name,
                          @QueryParam(value = "location") GeoPoint location) {
      return buildHalResponse(asRepresentationOf(storeService.update(id, name, location)));
   }

   @DELETE
   public Response delete(@PathParam(value = "id") String id) {
      storeService.delete(id);
      return Response.ok().build();
   }

   public static HalLink createRootLink(String rel) {
      return createSelfLinkBuilder().withRel(rel);
   }

   protected static HalRepresentation asRepresentationOf(StoreDto storeDto) {
      return halRepresentationFactory.createFor(storeDto).withLinks(createLinks(storeDto));
   }

   private static List<HalLink> createLinks(StoreDto storeDto) {
      return Collections.singletonList(createSelfLinkBuilder().pathArgs(storeDto.getId()).withSelfRel());
   }

   private static JaxrsHalResourceLinkBuilder createSelfLinkBuilder() {
      return JaxrsHalResourceLinkBuilder.linkTo(StoreResource.class).queryParams("name", "location");
   }
}
