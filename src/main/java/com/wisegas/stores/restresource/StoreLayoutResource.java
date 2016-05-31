package com.wisegas.stores.restresource;

import com.wisegas.common.lang.spacial.GeoPoint;
import com.wisegas.common.lang.spacial.GeoPolygon;
import com.wisegas.common.lang.value.CodeName;
import com.wisegas.common.webservices.hal.api.HalLink;
import com.wisegas.common.webservices.hal.api.HalRepresentation;
import com.wisegas.common.webservices.jaxrs.hal.JaxrsHalJsonResource;
import com.wisegas.common.webservices.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import com.wisegas.stores.service.api.StoreLayoutService;
import com.wisegas.stores.service.dto.*;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Path("/storeLayouts/{id}/")
public class StoreLayoutResource extends JaxrsHalJsonResource {

   private final StoreLayoutService storeLayoutService;

   @Inject
   public StoreLayoutResource(StoreLayoutService storeLayoutService) {
      this.storeLayoutService = storeLayoutService;
   }

   @GET
   public Response get(@PathParam("id") final String id) {
      return buildHalResponse(asRepresentationOf(storeLayoutService.get(id)));
   }

   @PUT
   @Path("outerOutline")
   public Response updateOuterOutline(@PathParam("id") final String id,
                                      @QueryParam("polygon") final GeoPolygon polygon) {
      StoreLayoutDto storeLayoutDto = storeLayoutService.updateOuterOutline(id, polygon);
      return buildHalResponse(asRepresentationOf(storeLayoutDto));
   }

   @PUT
   @Path("innerOutline")
   public Response updateInnerOutline(@PathParam("id") final String id,
                                      @QueryParam("polygon") final GeoPolygon polygon) {
      StoreLayoutDto storeLayoutDto = storeLayoutService.updateInnerOutline(id, polygon);
      return buildHalResponse(asRepresentationOf(storeLayoutDto));
   }

   @POST
   @Path("features")
   public Response addFeature(@PathParam("id") final String id,
                              @QueryParam("polygon") final GeoPolygon polygon) {
      FeatureDto featureDto = storeLayoutService.addFeature(id, polygon);
      return buildHalResponse(FeatureResource.asRepresentationOf(featureDto));
   }

   @PUT
   @Path("features/reshape")
   public Response reshapeFeature(@PathParam("id") final String id,
                                  @QueryParam("featureId") final String featureId,
                                  @QueryParam("polygon") final GeoPolygon polygon) {
      FeatureDto featureDto = storeLayoutService.reshapeFeature(id, featureId, polygon);
      return buildHalResponse(FeatureResource.asRepresentationOf(featureDto));
   }

   @POST
   @Path("nodes")
   public Response addNode(@PathParam("id") final String id,
                           @QueryParam("type") final String type,
                           @QueryParam("location") final GeoPoint location) {
      StoreLayoutUpdateDto<NodeDto> result = storeLayoutService.addNode(id, type, location);
      return buildHalResponse(NodeResource.asRepresentationOf(result.getTarget())
                                          .withEmbeddeds("affectedNodes", result.getAffectedNodes().stream()
                                                                                          .map(NodeResource::asRepresentationOf)
                                                                                          .collect(Collectors.toList())));
   }

   @PUT
   @Path("nodes/move")
   public Response moveNode(@PathParam("id") final String id,
                            @QueryParam("nodeId") final String nodeId,
                            @QueryParam("location") final GeoPoint location) {
      NodeDto nodeDto = storeLayoutService.moveNode(id, nodeId, location);
      return buildHalResponse(NodeResource.asRepresentationOf(nodeDto));
   }

   @POST
   @Path("nodes/items")
   public Response addNodeItem(@PathParam("id") final String id,
                               @QueryParam("nodeId") final String nodeId,
                               @QueryParam("item") final CodeName item) {
      StoreLayoutUpdateDto<NodeItemDto> result = storeLayoutService.addNodeItem(id, nodeId, item);
      return buildHalResponse(NodeItemResource.asRepresentationOf(result.getTarget())
                                              .withEmbeddeds("affectedNodes", result.getAffectedNodes().stream()
                                                                                              .map(NodeResource::asRepresentationOf)
                                                                                              .collect(Collectors.toList())));
   }

   public static HalLink createRootLink(String rel) {
      return createSelfLinkBuilder().withRel(rel);
   }

   protected static HalRepresentation asRepresentationOf(StoreLayoutDto storeLayoutDto) {
      return halRepresentationFactory.createFor(storeLayoutDto).withLinks(createLinks(storeLayoutDto));
   }

   private static List<HalLink> createLinks(StoreLayoutDto storeLayoutDto) {
      return Arrays.asList(
         createSelfLinkBuilder().pathArgs(storeLayoutDto.getId()).withSelfRel(),
         JaxrsHalResourceLinkBuilder.linkTo(StoreLayoutResource.class).method("updateOuterOutline").pathArgs(storeLayoutDto.getId()).queryParams("polygon").withRel("outerOutline"),
         JaxrsHalResourceLinkBuilder.linkTo(StoreLayoutResource.class).method("updateInnerOutline").pathArgs(storeLayoutDto.getId()).queryParams("polygon").withRel("innerOutline"),
         JaxrsHalResourceLinkBuilder.linkTo(StoreLayoutResource.class).method("addFeature").pathArgs(storeLayoutDto.getId()).queryParams("polygon").withRel("addFeature"),
         JaxrsHalResourceLinkBuilder.linkTo(StoreLayoutResource.class).method("reshapeFeature").pathArgs(storeLayoutDto.getId()).queryParams("featureId", "polygon").withRel("reshapeFeature"),
         JaxrsHalResourceLinkBuilder.linkTo(StoreLayoutResource.class).method("addNode").pathArgs(storeLayoutDto.getId()).queryParams("type", "location").withRel("addNode"),
         JaxrsHalResourceLinkBuilder.linkTo(StoreLayoutResource.class).method("moveNode").pathArgs(storeLayoutDto.getId()).queryParams("nodeId", "location").withRel("moveNode"),
         JaxrsHalResourceLinkBuilder.linkTo(StoreLayoutResource.class).method("addNodeItem").pathArgs(storeLayoutDto.getId()).queryParams("nodeId", "item").withRel("addNodeItem")
      );
   }

   private static JaxrsHalResourceLinkBuilder createSelfLinkBuilder() {
      return JaxrsHalResourceLinkBuilder.linkTo(StoreLayoutResource.class);
   }
}
