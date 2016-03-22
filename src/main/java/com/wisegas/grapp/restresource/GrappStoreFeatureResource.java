package com.wisegas.grapp.restresource;

import com.wisegas.common.webserver.hal.api.HalLink;
import com.wisegas.common.webserver.hal.api.HalRepresentation;
import com.wisegas.common.webserver.jersey.hal.JerseyHalJsonResource;
import com.wisegas.common.webserver.jersey.hal.JerseyHalResourceLinkBuilder;
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
public class GrappStoreFeatureResource extends JerseyHalJsonResource {

   private final GrappStoreFeatureService grappStoreFeatureService;

   @Inject
   public GrappStoreFeatureResource(GrappStoreFeatureService grappStoreFeatureService) {
      this.grappStoreFeatureService = grappStoreFeatureService;
   }

   @GET
   public Response get(@PathParam("id") final String id) {
      GrappStoreFeatureDTO grappStoreFeatureDTO = grappStoreFeatureService.get(id);
      return buildHalResponse(asRepresentationOf(grappStoreFeatureDTO));
   }

   protected static HalRepresentation asRepresentationOf(GrappStoreFeatureDTO grappStoreFeatureDTO) {
      return halRepresentationFactory.createFor(grappStoreFeatureDTO).withLinks(createLinks(grappStoreFeatureDTO));
   }

   private static List<HalLink> createLinks(GrappStoreFeatureDTO grappStoreFeatureDTO) {
      return Collections.singletonList(createSelfLinkBuilder().pathArgs(grappStoreFeatureDTO.getId()).withSelfRel());
   }

   private static JerseyHalResourceLinkBuilder createSelfLinkBuilder() {
      return JerseyHalResourceLinkBuilder.linkTo(GrappStoreFeatureResource.class);
   }
}
