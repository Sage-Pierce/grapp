package com.wisegas.grapp.storemanagement.restresource;

import com.wisegas.common.webserver.hal.api.HalLink;
import com.wisegas.common.webserver.hal.api.HalRepresentation;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalJsonResource;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import com.wisegas.grapp.storemanagement.service.api.GrappStoreFeatureService;
import com.wisegas.grapp.storemanagement.service.dto.GrappStoreFeatureDto;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Path("/features/{id}/")
public class GrappStoreFeatureResource extends JaxrsHalJsonResource {

   private final GrappStoreFeatureService grappStoreFeatureService;

   @Inject
   public GrappStoreFeatureResource(GrappStoreFeatureService grappStoreFeatureService) {
      this.grappStoreFeatureService = grappStoreFeatureService;
   }

   @GET
   public Response get(@PathParam("id") final String id) {
      return buildHalResponse(asRepresentationOf(grappStoreFeatureService.get(id)));
   }

   protected static HalRepresentation asRepresentationOf(GrappStoreFeatureDto grappStoreFeatureDto) {
      return halRepresentationFactory.createFor(grappStoreFeatureDto).withLinks(createLinks(grappStoreFeatureDto));
   }

   private static List<HalLink> createLinks(GrappStoreFeatureDto grappStoreFeatureDto) {
      return Collections.singletonList(createSelfLinkBuilder().pathArgs(grappStoreFeatureDto.getId()).withSelfRel());
   }

   private static JaxrsHalResourceLinkBuilder createSelfLinkBuilder() {
      return JaxrsHalResourceLinkBuilder.linkTo(GrappStoreFeatureResource.class);
   }
}
