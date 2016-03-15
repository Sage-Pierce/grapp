package com.wisegas.grapp.restresource;

import com.wisegas.common.webserver.hal.HALResource;
import com.wisegas.common.webserver.hal.HALResourceLinkBuilder;
import com.wisegas.common.webserver.hal.api.HALLink;
import com.wisegas.common.webserver.hal.api.HALRepresentation;
import com.wisegas.grapp.service.api.GrappStoreFeatureService;
import com.wisegas.grapp.service.dto.GrappStoreFeatureDTO;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Path("/features/{id}/")
public class GrappStoreFeatureResource extends HALResource {

   private final GrappStoreFeatureService grappStoreFeatureService;

   @Inject
   public GrappStoreFeatureResource(GrappStoreFeatureService grappStoreFeatureService) {
      this.grappStoreFeatureService = grappStoreFeatureService;
   }

   @GET
   public Response get(@PathParam("id") final String id) {
      GrappStoreFeatureDTO grappStoreFeatureDTO = grappStoreFeatureService.get(id);
      return buildHALResponse(asRepresentationOf(grappStoreFeatureDTO));
   }

   protected static HALRepresentation asRepresentationOf(GrappStoreFeatureDTO grappStoreFeatureDTO) {
      return halRepresentationFactory.createFor(grappStoreFeatureDTO).withLinks(createLinks(grappStoreFeatureDTO));
   }

   private static List<HALLink> createLinks(GrappStoreFeatureDTO grappStoreFeatureDTO) {
      return Collections.singletonList(createSelfLinkBuilder().pathArgs(grappStoreFeatureDTO.getId()).withSelfRel());
   }

   private static HALResourceLinkBuilder createSelfLinkBuilder() {
      return HALResourceLinkBuilder.linkTo(GrappStoreFeatureResource.class);
   }
}
