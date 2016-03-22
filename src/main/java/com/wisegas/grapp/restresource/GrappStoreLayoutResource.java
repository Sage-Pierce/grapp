package com.wisegas.grapp.restresource;

import com.wisegas.common.lang.value.GeoPoint;
import com.wisegas.common.lang.value.GeoPolygon;
import com.wisegas.common.webserver.hal.api.HalLink;
import com.wisegas.common.webserver.hal.api.HalRepresentation;
import com.wisegas.common.webserver.jersey.hal.JerseyHalJsonResource;
import com.wisegas.common.webserver.jersey.hal.JerseyHalResourceLinkBuilder;
import com.wisegas.grapp.service.api.GrappStoreLayoutService;
import com.wisegas.grapp.service.dto.GrappStoreFeatureDTO;
import com.wisegas.grapp.service.dto.GrappStoreLayoutDTO;
import com.wisegas.grapp.service.dto.GrappStoreLayoutUpdateResultDTO;
import com.wisegas.grapp.service.dto.GrappStoreNodeDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Path("/storeLayouts/{id}/")
public class GrappStoreLayoutResource extends JerseyHalJsonResource {

   private final GrappStoreLayoutService grappStoreLayoutService;

   @Inject
   public GrappStoreLayoutResource(GrappStoreLayoutService grappStoreLayoutService) {
      this.grappStoreLayoutService = grappStoreLayoutService;
   }

   @GET
   public Response get(@PathParam("id") final String id) {
      GrappStoreLayoutDTO grappStoreLayoutDTO = grappStoreLayoutService.get(id);
      return buildHalResponse(asRepresentationOf(grappStoreLayoutDTO));
   }

   @PUT
   @Path("outerOutline")
   public Response updateOuterOutline(@PathParam("id") final String id,
                                      @QueryParam("polygon") final GeoPolygon polygon) {
      GrappStoreLayoutDTO grappStoreLayoutDTO = grappStoreLayoutService.updateOuterOutline(id, polygon);
      return buildHalResponse(asRepresentationOf(grappStoreLayoutDTO));
   }

   @PUT
   @Path("innerOutline")
   public Response updateInnerOutline(@PathParam("id") final String id,
                                      @QueryParam("polygon") final GeoPolygon polygon) {
      GrappStoreLayoutDTO grappStoreLayoutDTO = grappStoreLayoutService.updateInnerOutline(id, polygon);
      return buildHalResponse(asRepresentationOf(grappStoreLayoutDTO));
   }

   @POST
   @Path("features")
   public Response addFeature(@PathParam("id") final String id,
                              @QueryParam("polygon") final GeoPolygon polygon) {
      GrappStoreFeatureDTO grappStoreFeatureDTO = grappStoreLayoutService.addFeature(id, polygon);
      return buildHalResponse(GrappStoreFeatureResource.asRepresentationOf(grappStoreFeatureDTO));
   }

   @PUT
   @Path("features/reshape")
   public Response reshapeFeature(@PathParam("id") final String id,
                                  @QueryParam("featureID") final String featureID,
                                  @QueryParam("polygon") final GeoPolygon polygon) {
      GrappStoreFeatureDTO grappStoreFeatureDTO = grappStoreLayoutService.reshapeFeature(id, featureID, polygon);
      return buildHalResponse(GrappStoreFeatureResource.asRepresentationOf(grappStoreFeatureDTO));
   }

   @DELETE
   @Path("features/remove")
   public Response removeFeature(@PathParam("id") final String id,
                                 @QueryParam("featureID") final String featureID) {
      grappStoreLayoutService.removeFeature(id, featureID);
      return Response.ok().build();
   }

   @POST
   @Path("nodes")
   public Response addNode(@PathParam("id") final String id,
                           @QueryParam("type") final String type,
                           @QueryParam("location") final GeoPoint location) {
      GrappStoreLayoutUpdateResultDTO<GrappStoreNodeDTO> result = grappStoreLayoutService.addNode(id, type, location);
      return buildHalResponse(GrappStoreNodeResource.asRepresentationOf(result.getTarget())
                                                    .withEmbeddeds("affectedNodes", result.getAffectedNodes().stream()
                                                                                          .map(GrappStoreNodeResource::asRepresentationOf)
                                                                                          .collect(Collectors.toList())));
   }

   @PUT
   @Path("nodes/move")
   public Response moveNode(@PathParam("id") final String id,
                            @QueryParam("nodeID") final String nodeID,
                            @QueryParam("location") final GeoPoint location) {
      GrappStoreNodeDTO grappStoreNodeDTO = grappStoreLayoutService.moveNode(id, nodeID, location);
      return buildHalResponse(GrappStoreNodeResource.asRepresentationOf(grappStoreNodeDTO));
   }

   @DELETE
   @Path("nodes/remove")
   public Response removeNode(@PathParam("id") final String id,
                              @QueryParam("nodeID") final String nodeID) {
      grappStoreLayoutService.removeNode(id, nodeID);
      return Response.ok().build();
   }

   protected static HalRepresentation asRepresentationOf(GrappStoreLayoutDTO grappStoreLayoutDTO) {
      return halRepresentationFactory.createFor(grappStoreLayoutDTO).withLinks(createLinks(grappStoreLayoutDTO));
   }

   protected static HalLink createRootLink(String rel) {
      return createSelfLinkBuilder().withRel(rel);
   }

   private static List<HalLink> createLinks(GrappStoreLayoutDTO grappStoreLayoutDTO) {
      return Arrays.asList(
         createSelfLinkBuilder().pathArgs(grappStoreLayoutDTO.getId()).withSelfRel(),
         JerseyHalResourceLinkBuilder.linkTo(GrappStoreLayoutResource.class).method("updateOuterOutline").pathArgs(grappStoreLayoutDTO.getId()).queryParams("polygon").withRel("outerOutline"),
         JerseyHalResourceLinkBuilder.linkTo(GrappStoreLayoutResource.class).method("updateInnerOutline").pathArgs(grappStoreLayoutDTO.getId()).queryParams("polygon").withRel("innerOutline"),
         JerseyHalResourceLinkBuilder.linkTo(GrappStoreLayoutResource.class).method("addFeature").pathArgs(grappStoreLayoutDTO.getId()).queryParams("polygon").withRel("addFeature"),
         JerseyHalResourceLinkBuilder.linkTo(GrappStoreLayoutResource.class).method("reshapeFeature").pathArgs(grappStoreLayoutDTO.getId()).queryParams("featureID", "polygon").withRel("reshapeFeature"),
         JerseyHalResourceLinkBuilder.linkTo(GrappStoreLayoutResource.class).method("removeFeature").pathArgs(grappStoreLayoutDTO.getId()).queryParams("featureID").withRel("removeFeature"),
         JerseyHalResourceLinkBuilder.linkTo(GrappStoreLayoutResource.class).method("addNode").pathArgs(grappStoreLayoutDTO.getId()).queryParams("type","location").withRel("addNode"),
         JerseyHalResourceLinkBuilder.linkTo(GrappStoreLayoutResource.class).method("moveNode").pathArgs(grappStoreLayoutDTO.getId()).queryParams("nodeID", "location").withRel("moveNode"),
         JerseyHalResourceLinkBuilder.linkTo(GrappStoreLayoutResource.class).method("removeNode").pathArgs(grappStoreLayoutDTO.getId()).queryParams("nodeID").withRel("removeNode")
      );
   }

   private static JerseyHalResourceLinkBuilder createSelfLinkBuilder() {
      return JerseyHalResourceLinkBuilder.linkTo(GrappStoreLayoutResource.class);
   }
}
