package com.wisegas.grapp.storemanagement.restresource;

import com.wisegas.common.lang.value.CodeName;
import com.wisegas.common.lang.value.GeoPoint;
import com.wisegas.common.lang.value.GeoPolygon;
import com.wisegas.common.webserver.hal.api.HalLink;
import com.wisegas.common.webserver.hal.api.HalRepresentation;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalJsonResource;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import com.wisegas.grapp.storemanagement.service.api.LayoutService;
import com.wisegas.grapp.storemanagement.service.dto.*;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Path("/storeLayouts/{id}/")
public class LayoutResource extends JaxrsHalJsonResource {

   private final LayoutService layoutService;

   @Inject
   public LayoutResource(LayoutService layoutService) {
      this.layoutService = layoutService;
   }

   @GET
   public Response get(@PathParam("id") final String id) {
      return buildHalResponse(asRepresentationOf(layoutService.get(id)));
   }

   @PUT
   @Path("outerOutline")
   public Response updateOuterOutline(@PathParam("id") final String id,
                                      @QueryParam("polygon") final GeoPolygon polygon) {
      LayoutDto layoutDto = layoutService.updateOuterOutline(id, polygon);
      return buildHalResponse(asRepresentationOf(layoutDto));
   }

   @PUT
   @Path("innerOutline")
   public Response updateInnerOutline(@PathParam("id") final String id,
                                      @QueryParam("polygon") final GeoPolygon polygon) {
      LayoutDto layoutDto = layoutService.updateInnerOutline(id, polygon);
      return buildHalResponse(asRepresentationOf(layoutDto));
   }

   @POST
   @Path("features")
   public Response addFeature(@PathParam("id") final String id,
                              @QueryParam("polygon") final GeoPolygon polygon) {
      FeatureDto featureDto = layoutService.addFeature(id, polygon);
      return buildHalResponse(FeatureResource.asRepresentationOf(featureDto));
   }

   @PUT
   @Path("features/reshape")
   public Response reshapeFeature(@PathParam("id") final String id,
                                  @QueryParam("featureID") final String featureID,
                                  @QueryParam("polygon") final GeoPolygon polygon) {
      FeatureDto featureDto = layoutService.reshapeFeature(id, featureID, polygon);
      return buildHalResponse(FeatureResource.asRepresentationOf(featureDto));
   }

   @DELETE
   @Path("features/remove")
   public Response removeFeature(@PathParam("id") final String id,
                                 @QueryParam("featureID") final String featureID) {
      layoutService.removeFeature(id, featureID);
      return Response.ok().build();
   }

   @POST
   @Path("nodes")
   public Response addNode(@PathParam("id") final String id,
                           @QueryParam("type") final String type,
                           @QueryParam("location") final GeoPoint location) {
      LayoutUpdateDto<NodeDto> result = layoutService.addNode(id, type, location);
      return buildHalResponse(NodeResource.asRepresentationOf(result.getTarget())
                                          .withEmbeddeds("affectedNodes", result.getAffectedNodes().stream()
                                                                                          .map(NodeResource::asRepresentationOf)
                                                                                          .collect(Collectors.toList())));
   }

   @PUT
   @Path("nodes/move")
   public Response moveNode(@PathParam("id") final String id,
                            @QueryParam("nodeID") final String nodeID,
                            @QueryParam("location") final GeoPoint location) {
      NodeDto nodeDto = layoutService.moveNode(id, nodeID, location);
      return buildHalResponse(NodeResource.asRepresentationOf(nodeDto));
   }

   @DELETE
   @Path("nodes/remove")
   public Response removeNode(@PathParam("id") final String id,
                              @QueryParam("nodeID") final String nodeID) {
      layoutService.removeNode(id, nodeID);
      return Response.ok().build();
   }

   @POST
   @Path("nodes/items")
   public Response addNodeItem(@PathParam("id") final String id,
                               @QueryParam("nodeID") final String nodeID,
                               @QueryParam("item") final CodeName item) {
      LayoutUpdateDto<NodeItemDto> result = layoutService.addNodeItem(id, nodeID, item);
      return buildHalResponse(NodeItemResource.asRepresentationOf(result.getTarget())
                                              .withEmbeddeds("affectedNodes", result.getAffectedNodes().stream()
                                                                                              .map(NodeResource::asRepresentationOf)
                                                                                              .collect(Collectors.toList())));
   }

   public static HalLink createRootLink(String rel) {
      return createSelfLinkBuilder().withRel(rel);
   }

   protected static HalRepresentation asRepresentationOf(LayoutDto layoutDto) {
      return halRepresentationFactory.createFor(layoutDto).withLinks(createLinks(layoutDto));
   }

   private static List<HalLink> createLinks(LayoutDto layoutDto) {
      return Arrays.asList(
         createSelfLinkBuilder().pathArgs(layoutDto.getId()).withSelfRel(),
         JaxrsHalResourceLinkBuilder.linkTo(LayoutResource.class).method("updateOuterOutline").pathArgs(layoutDto.getId()).queryParams("polygon").withRel("outerOutline"),
         JaxrsHalResourceLinkBuilder.linkTo(LayoutResource.class).method("updateInnerOutline").pathArgs(layoutDto.getId()).queryParams("polygon").withRel("innerOutline"),
         JaxrsHalResourceLinkBuilder.linkTo(LayoutResource.class).method("addFeature").pathArgs(layoutDto.getId()).queryParams("polygon").withRel("addFeature"),
         JaxrsHalResourceLinkBuilder.linkTo(LayoutResource.class).method("reshapeFeature").pathArgs(layoutDto.getId()).queryParams("featureID", "polygon").withRel("reshapeFeature"),
         JaxrsHalResourceLinkBuilder.linkTo(LayoutResource.class).method("removeFeature").pathArgs(layoutDto.getId()).queryParams("featureID").withRel("removeFeature"),
         JaxrsHalResourceLinkBuilder.linkTo(LayoutResource.class).method("addNode").pathArgs(layoutDto.getId()).queryParams("type", "location").withRel("addNode"),
         JaxrsHalResourceLinkBuilder.linkTo(LayoutResource.class).method("moveNode").pathArgs(layoutDto.getId()).queryParams("nodeID", "location").withRel("moveNode"),
         JaxrsHalResourceLinkBuilder.linkTo(LayoutResource.class).method("removeNode").pathArgs(layoutDto.getId()).queryParams("nodeID").withRel("removeNode"),
         JaxrsHalResourceLinkBuilder.linkTo(LayoutResource.class).method("addNodeItem").pathArgs(layoutDto.getId()).queryParams("nodeID", "item").withRel("addNodeItem")
      );
   }

   private static JaxrsHalResourceLinkBuilder createSelfLinkBuilder() {
      return JaxrsHalResourceLinkBuilder.linkTo(LayoutResource.class);
   }
}
