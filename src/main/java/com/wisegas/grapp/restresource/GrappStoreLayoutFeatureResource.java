package com.wisegas.grapp.restresource;

import com.wisegas.grapp.service.api.GrappStoreLayoutFeatureService;
import com.wisegas.grapp.service.dto.GrappStoreLayoutFeatureDTO;
import com.wisegas.webserver.hal.HALResource;
import com.wisegas.webserver.hal.HALResourceLinkBuilder;
import com.wisegas.webserver.hal.api.HALLink;
import com.wisegas.webserver.hal.api.HALRepresentation;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Path("/features/{id}")
public class GrappStoreLayoutFeatureResource extends HALResource {

   private final GrappStoreLayoutFeatureService grappStoreLayoutFeatureService;

   @Inject
   public GrappStoreLayoutFeatureResource(GrappStoreLayoutFeatureService grappStoreLayoutFeatureService) {
      this.grappStoreLayoutFeatureService = grappStoreLayoutFeatureService;
   }

   @GET
   public Response findByID(@PathParam("id") final String id) {
      GrappStoreLayoutFeatureDTO grappStoreLayoutFeatureDTO = grappStoreLayoutFeatureService.findByID(id);
      HALRepresentation halRepresentation = halRepresentationFactory.createFor(grappStoreLayoutFeatureDTO).withLinks(createLinks(grappStoreLayoutFeatureDTO));
      return buildHALResponse(halRepresentation);
   }

   protected static List<HALLink> createLinks(GrappStoreLayoutFeatureDTO grappStoreLayoutFeatureDTO) {
      return Collections.singletonList(
         HALResourceLinkBuilder.linkTo(GrappStoreLayoutFeatureResource.class).pathArgs(grappStoreLayoutFeatureDTO.getId()).withSelfRel()
      );
   }
}
