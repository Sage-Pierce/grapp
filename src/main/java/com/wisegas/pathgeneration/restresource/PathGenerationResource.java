package com.wisegas.pathgeneration.restresource;

import com.wisegas.common.lang.spacial.Point;
import com.wisegas.common.webservices.hal.api.HalLink;
import com.wisegas.common.webservices.hal.api.HalRepresentation;
import com.wisegas.common.webservices.jaxrs.hal.JaxrsHalJsonResource;
import com.wisegas.common.webservices.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import com.wisegas.pathgeneration.service.api.PathService;
import com.wisegas.pathgeneration.service.dto.PathDto;
import com.wisegas.pathgeneration.service.dto.PathPolygonsDto;
import com.wisegas.pathgeneration.service.dto.WaypointsDto;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Path("/pathGeneration/")
public class PathGenerationResource extends JaxrsHalJsonResource {

   private final PathService pathService;

   @Inject
   public PathGenerationResource(PathService pathService) {
      this.pathService = pathService;
   }

   @GET
   public Response getRoot() {
      return buildHalResponse(halRepresentationFactory.createForLinks(createLinks()));
   }

   @PUT
   @Consumes(MediaType.APPLICATION_JSON)
   public Response generatePath(@QueryParam("start") final Point start,
                                @QueryParam("finish") final Point finish,
                                @QueryParam("waypoints") final WaypointsDto waypoints,
                                final PathPolygonsDto pathPolygons) {
      return buildHalResponse(asRepresentationOf(pathService.generatePath(start, finish, waypoints, pathPolygons)));
   }

   public static HalLink createRootLink(String rel) {
      return createSelfLinkBuilder().withRel(rel);
   }

   protected static HalRepresentation asRepresentationOf(PathDto pathDto) {
      return halRepresentationFactory.createFor(pathDto).withLinks(createLinks());
   }

   private static List<HalLink> createLinks() {
      return Collections.singletonList(createSelfLinkBuilder().withSelfRel());
   }

   private static JaxrsHalResourceLinkBuilder createSelfLinkBuilder() {
      return JaxrsHalResourceLinkBuilder.linkTo(PathGenerationResource.class).queryParams("start", "finish", "waypoints");
   }
}
