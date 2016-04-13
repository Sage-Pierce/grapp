package com.wisegas.storemanagement.restresource;

import com.wisegas.common.lang.value.GeoPoint;
import com.wisegas.common.webserver.hal.api.HalLink;
import com.wisegas.common.webserver.hal.api.HalRepresentation;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalJsonResource;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import com.wisegas.storemanagement.service.api.StoreService;
import com.wisegas.storemanagement.service.dto.StoreDto;

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
   public Response get(@PathParam(value = "id") final String id) {
      return buildHalResponse(asRepresentationOf(storeService.get(id)));
   }

   @PUT
   public Response update(@PathParam(value = "id") final String id,
                          @QueryParam(value = "name") final String name,
                          @QueryParam(value = "location") final GeoPoint location) {
      StoreDto storeDto = storeService.update(id, name, location);
      return buildHalResponse(asRepresentationOf(storeDto));
   }

   @DELETE
   public Response delete(@PathParam(value = "id") final String id) {
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
