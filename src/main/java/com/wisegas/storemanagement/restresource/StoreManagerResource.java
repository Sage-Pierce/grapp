package com.wisegas.storemanagement.restresource;

import com.wisegas.common.lang.spacial.GeoPoint;
import com.wisegas.common.lang.value.Email;
import com.wisegas.common.webservices.hal.api.HalLink;
import com.wisegas.common.webservices.hal.api.HalRepresentation;
import com.wisegas.common.webservices.jaxrs.hal.JaxrsHalJsonResource;
import com.wisegas.common.webservices.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import com.wisegas.storemanagement.service.api.StoreManagerService;
import com.wisegas.storemanagement.service.dto.StoreManagerDto;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Path("/storeManagers/{id}/")
public class StoreManagerResource extends JaxrsHalJsonResource {

   private final StoreManagerService storeManagerService;

   @Inject
   public StoreManagerResource(StoreManagerService storeManagerService) {
      this.storeManagerService = storeManagerService;
   }

   @POST
   @Path("addStore")
   public Response addStore(@PathParam("id") final Email email,
                            @QueryParam("name") final String name,
                            @QueryParam("location") final GeoPoint location) {
      return buildHalResponse(StoreResource.asRepresentationOf(storeManagerService.addStore(email, name, location)));
   }

   public static HalLink createRootLink(String rel) {
      return createSelfLinkBuilder().withRel(rel);
   }

   protected static HalRepresentation asRepresentationOf(StoreManagerDto storeManagerDto) {
      return halRepresentationFactory.createFor(storeManagerDto).withLinks(createLinks(storeManagerDto));
   }

   private static List<HalLink> createLinks(StoreManagerDto storeManagerDto) {
      return Arrays.asList(
         createSelfLinkBuilder().pathArgs(storeManagerDto.getEmail()).withSelfRel(),
         JaxrsHalResourceLinkBuilder.linkTo(StoreManagerResource.class).method("addStore").pathArgs(storeManagerDto.getEmail()).queryParams("name", "location").withRel("addStore")
      );
   }

   private static JaxrsHalResourceLinkBuilder createSelfLinkBuilder() {
      return JaxrsHalResourceLinkBuilder.linkTo(StoreManagerResource.class);
   }
}
