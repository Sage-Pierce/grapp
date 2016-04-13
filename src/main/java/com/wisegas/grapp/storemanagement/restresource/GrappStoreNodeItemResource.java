package com.wisegas.grapp.storemanagement.restresource;

import com.wisegas.common.webserver.hal.api.HalLink;
import com.wisegas.common.webserver.hal.api.HalRepresentation;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalJsonResource;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import com.wisegas.grapp.storemanagement.service.api.GrappStoreNodeItemService;
import com.wisegas.grapp.storemanagement.service.dto.GrappStoreNodeItemDto;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Path("/nodeItems/{id}")
public class GrappStoreNodeItemResource extends JaxrsHalJsonResource {

   private final GrappStoreNodeItemService grappStoreNodeItemService;

   @Inject
   public GrappStoreNodeItemResource(GrappStoreNodeItemService grappStoreNodeItemService) {
      this.grappStoreNodeItemService = grappStoreNodeItemService;
   }

   @GET
   public Response get(@PathParam("id") final String id) {
      return buildHalResponse(asRepresentationOf(grappStoreNodeItemService.get(id)));
   }

   protected static HalRepresentation asRepresentationOf(GrappStoreNodeItemDto grappStoreNodeItemDTO) {
      return halRepresentationFactory.createFor(grappStoreNodeItemDTO).withLinks(createLinks(grappStoreNodeItemDTO));
   }

   private static List<HalLink> createLinks(GrappStoreNodeItemDto grappStoreNodeItemDTO) {
      return Collections.singletonList(createSelfLinkBuilder().pathArgs(grappStoreNodeItemDTO.getId()).withSelfRel());
   }

   private static JaxrsHalResourceLinkBuilder createSelfLinkBuilder() {
      return JaxrsHalResourceLinkBuilder.linkTo(GrappStoreNodeItemResource.class);
   }
}
