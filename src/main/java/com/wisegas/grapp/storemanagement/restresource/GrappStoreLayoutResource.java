package com.wisegas.grapp.storemanagement.restresource;

import com.wisegas.common.lang.value.CodeName;
import com.wisegas.common.lang.value.GeoPoint;
import com.wisegas.common.lang.value.GeoPolygon;
import com.wisegas.common.webserver.hal.api.HalLink;
import com.wisegas.common.webserver.hal.api.HalRepresentation;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalJsonResource;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import com.wisegas.grapp.storemanagement.service.api.GrappStoreLayoutService;
import com.wisegas.grapp.storemanagement.service.dto.*;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Path("/storeLayouts/{id}/")
public class GrappStoreLayoutResource extends JaxrsHalJsonResource {

   private final GrappStoreLayoutService grappStoreLayoutService;

   @Inject
   public GrappStoreLayoutResource(GrappStoreLayoutService grappStoreLayoutService) {
      this.grappStoreLayoutService = grappStoreLayoutService;
   }

   @GET
   public Response get(@PathParam("id") final String id) {
      return buildHalResponse(asRepresentationOf(grappStoreLayoutService.get(id)));
   }

   @PUT
   @Path("outerOutline")
   public Response updateOuterOutline(@PathParam("id") final String id,
                                      @QueryParam("polygon") final GeoPolygon polygon) {
      GrappStoreLayoutDto grappStoreLayoutDto = grappStoreLayoutService.updateOuterOutline(id, polygon);
      return buildHalResponse(asRepresentationOf(grappStoreLayoutDto));
   }

   @PUT
   @Path("innerOutline")
   public Response updateInnerOutline(@PathParam("id") final String id,
                                      @QueryParam("polygon") final GeoPolygon polygon) {
      GrappStoreLayoutDto grappStoreLayoutDto = grappStoreLayoutService.updateInnerOutline(id, polygon);
      return buildHalResponse(asRepresentationOf(grappStoreLayoutDto));
   }

   @POST
   @Path("features")
   public Response addFeature(@PathParam("id") final String id,
                              @QueryParam("polygon") final GeoPolygon polygon) {
      GrappStoreFeatureDto grappStoreFeatureDto = grappStoreLayoutService.addFeature(id, polygon);
      return buildHalResponse(GrappStoreFeatureResource.asRepresentationOf(grappStoreFeatureDto));
   }

   @PUT
   @Path("features/reshape")
   public Response reshapeFeature(@PathParam("id") final String id,
                                  @QueryParam("featureID") final String featureID,
                                  @QueryParam("polygon") final GeoPolygon polygon) {
      GrappStoreFeatureDto grappStoreFeatureDto = grappStoreLayoutService.reshapeFeature(id, featureID, polygon);
      return buildHalResponse(GrappStoreFeatureResource.asRepresentationOf(grappStoreFeatureDto));
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
      GrappStoreLayoutUpdateResultDTOO<GrappStoreNodeDto> result = grappStoreLayoutService.addNode(id, type, location);
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
      GrappStoreNodeDto grappStoreNodeDto = grappStoreLayoutService.moveNode(id, nodeID, location);
      return buildHalResponse(GrappStoreNodeResource.asRepresentationOf(grappStoreNodeDto));
   }

   @DELETE
   @Path("nodes/remove")
   public Response removeNode(@PathParam("id") final String id,
                              @QueryParam("nodeID") final String nodeID) {
      grappStoreLayoutService.removeNode(id, nodeID);
      return Response.ok().build();
   }

   @POST
   @Path("nodes/items")
   public Response addNodeItem(@PathParam("id") final String id,
                               @QueryParam("nodeID") final String nodeID,
                               @QueryParam("item") final CodeName item) {
      GrappStoreLayoutUpdateResultDTOO<GrappStoreNodeItemDto> result = grappStoreLayoutService.addNodeItem(id, nodeID, item);
      return buildHalResponse(GrappStoreNodeItemResource.asRepresentationOf(result.getTarget())
                                                        .withEmbeddeds("affectedNodes", result.getAffectedNodes().stream()
                                                                                              .map(GrappStoreNodeResource::asRepresentationOf)
                                                                                              .collect(Collectors.toList())));
   }

   public static HalLink createRootLink(String rel) {
      return createSelfLinkBuilder().withRel(rel);
   }

   protected static HalRepresentation asRepresentationOf(GrappStoreLayoutDto grappStoreLayoutDto) {
      return halRepresentationFactory.createFor(grappStoreLayoutDto).withLinks(createLinks(grappStoreLayoutDto));
   }

   private static List<HalLink> createLinks(GrappStoreLayoutDto grappStoreLayoutDto) {
      return Arrays.asList(
         createSelfLinkBuilder().pathArgs(grappStoreLayoutDto.getId()).withSelfRel(),
         JaxrsHalResourceLinkBuilder.linkTo(GrappStoreLayoutResource.class).method("updateOuterOutline").pathArgs(grappStoreLayoutDto.getId()).queryParams("polygon").withRel("outerOutline"),
         JaxrsHalResourceLinkBuilder.linkTo(GrappStoreLayoutResource.class).method("updateInnerOutline").pathArgs(grappStoreLayoutDto.getId()).queryParams("polygon").withRel("innerOutline"),
         JaxrsHalResourceLinkBuilder.linkTo(GrappStoreLayoutResource.class).method("addFeature").pathArgs(grappStoreLayoutDto.getId()).queryParams("polygon").withRel("addFeature"),
         JaxrsHalResourceLinkBuilder.linkTo(GrappStoreLayoutResource.class).method("reshapeFeature").pathArgs(grappStoreLayoutDto.getId()).queryParams("featureID", "polygon").withRel("reshapeFeature"),
         JaxrsHalResourceLinkBuilder.linkTo(GrappStoreLayoutResource.class).method("removeFeature").pathArgs(grappStoreLayoutDto.getId()).queryParams("featureID").withRel("removeFeature"),
         JaxrsHalResourceLinkBuilder.linkTo(GrappStoreLayoutResource.class).method("addNode").pathArgs(grappStoreLayoutDto.getId()).queryParams("type", "location").withRel("addNode"),
         JaxrsHalResourceLinkBuilder.linkTo(GrappStoreLayoutResource.class).method("moveNode").pathArgs(grappStoreLayoutDto.getId()).queryParams("nodeID", "location").withRel("moveNode"),
         JaxrsHalResourceLinkBuilder.linkTo(GrappStoreLayoutResource.class).method("removeNode").pathArgs(grappStoreLayoutDto.getId()).queryParams("nodeID").withRel("removeNode"),
         JaxrsHalResourceLinkBuilder.linkTo(GrappStoreLayoutResource.class).method("addNodeItem").pathArgs(grappStoreLayoutDto.getId()).queryParams("nodeID", "item").withRel("addNodeItem")
      );
   }

   private static JaxrsHalResourceLinkBuilder createSelfLinkBuilder() {
      return JaxrsHalResourceLinkBuilder.linkTo(GrappStoreLayoutResource.class);
   }
}
