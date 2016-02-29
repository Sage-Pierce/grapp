package com.wisegas.grapp.restresource;

import com.wisegas.grapp.service.api.GrappStoreLayoutService;
import com.wisegas.grapp.service.dto.GrappStoreLayoutDTO;
import com.wisegas.grapp.service.dto.GrappStoreFeatureDTO;
import com.wisegas.grapp.service.dto.GrappStoreNodeDTO;
import com.wisegas.lang.GeoPoint;
import com.wisegas.lang.GeoPolygon;
import com.wisegas.webserver.hal.HALResource;
import com.wisegas.webserver.hal.HALResourceLinkBuilder;
import com.wisegas.webserver.hal.api.HALLink;
import com.wisegas.webserver.hal.api.HALRepresentation;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Path("/storeLayouts/{id}/")
public class GrappStoreLayoutResource extends HALResource {

   private final GrappStoreLayoutService grappStoreLayoutService;

   @Inject
   public GrappStoreLayoutResource(GrappStoreLayoutService grappStoreLayoutService) {
      this.grappStoreLayoutService = grappStoreLayoutService;
   }

   @GET
   public Response findByID(@PathParam("id") final String id) {
      GrappStoreLayoutDTO grappStoreLayoutDTO = grappStoreLayoutService.findByID(id);
      HALRepresentation halRepresentation = halRepresentationFactory.createFor(grappStoreLayoutDTO).withLinks(createLinks(grappStoreLayoutDTO));
      return buildHALResponse(halRepresentation);
   }

   @PUT
   @Path("outerOutline")
   public Response updateOuterOutline(@PathParam("id") final String id,
                                      @QueryParam("polygon") final GeoPolygon polygon) {
      GrappStoreLayoutDTO grappStoreLayoutDTO = grappStoreLayoutService.updateOuterOutline(id, polygon);
      HALRepresentation halRepresentation = halRepresentationFactory.createFor(grappStoreLayoutDTO).withLinks(createLinks(grappStoreLayoutDTO));
      return buildHALResponse(halRepresentation);
   }

   @PUT
   @Path("innerOutline")
   public Response updateInnerOutline(@PathParam("id") final String id,
                                      @QueryParam("polygon") final GeoPolygon polygon) {
      GrappStoreLayoutDTO grappStoreLayoutDTO = grappStoreLayoutService.updateInnerOutline(id, polygon);
      HALRepresentation halRepresentation = halRepresentationFactory.createFor(grappStoreLayoutDTO).withLinks(createLinks(grappStoreLayoutDTO));
      return buildHALResponse(halRepresentation);
   }

   @POST
   @Path("features")
   public Response addFeature(@PathParam("id") final String layoutID,
                              @QueryParam("polygon") final GeoPolygon polygon) {
      GrappStoreFeatureDTO grappStoreFeatureDTO = grappStoreLayoutService.addFeature(layoutID, polygon);
      HALRepresentation halRepresentation = halRepresentationFactory.createFor(grappStoreFeatureDTO).withLinks(GrappStoreFeatureResource.createLinks(grappStoreFeatureDTO));
      return buildHALResponse(halRepresentation);
   }

   @PUT
   @Path("features/reshape")
   public Response reshapeFeature(@PathParam("id") final String layoutID,
                                  @QueryParam("featureID") final String featureID,
                                  @QueryParam("polygon") final GeoPolygon polygon) {
      GrappStoreFeatureDTO grappStoreFeatureDTO = grappStoreLayoutService.reshapeFeature(layoutID, featureID, polygon);
      HALRepresentation halRepresentation = halRepresentationFactory.createFor(grappStoreFeatureDTO).withLinks(GrappStoreFeatureResource.createLinks(grappStoreFeatureDTO));
      return buildHALResponse(halRepresentation);
   }

   @DELETE
   @Path("features/remove")
   public Response removeFeature(@PathParam("id") final String layoutID,
                                 @QueryParam("featureID") final String featureID) {
      grappStoreLayoutService.removeFeature(layoutID, featureID);
      return Response.ok().build();
   }

   @POST
   @Path("nodes")
   public Response addNode(@PathParam("id") final String layoutID,
                           @QueryParam("location") final GeoPoint location) {
      GrappStoreNodeDTO grappStoreNodeDTO = grappStoreLayoutService.addNode(layoutID, location);
      HALRepresentation halRepresentation = halRepresentationFactory.createFor(grappStoreNodeDTO).withLinks(GrappStoreNodeResource.createLinks(grappStoreNodeDTO));
      return buildHALResponse(halRepresentation);
   }

   @PUT
   @Path("nodes/move")
   public Response moveNode(@PathParam("id") final String layoutID,
                            @QueryParam("nodeID") final String nodeID,
                            @QueryParam("location") final GeoPoint location) {
      GrappStoreNodeDTO grappStoreNodeDTO = grappStoreLayoutService.moveNode(layoutID, nodeID, location);
      HALRepresentation halRepresentation = halRepresentationFactory.createFor(grappStoreNodeDTO).withLinks(GrappStoreNodeResource.createLinks(grappStoreNodeDTO));
      return buildHALResponse(halRepresentation);
   }

   @DELETE
   @Path("nodes/remove")
   public Response removeNode(@PathParam("id") final String layoutID,
                              @QueryParam("nodeID") final String nodeID) {
      grappStoreLayoutService.removeNode(layoutID, nodeID);
      return Response.ok().build();
   }

   protected static List<HALLink> createLinks(GrappStoreLayoutDTO grappStoreLayoutDTO) {
      return Arrays.asList(
         HALResourceLinkBuilder.linkTo(GrappStoreLayoutResource.class).pathArgs(grappStoreLayoutDTO.getId()).withSelfRel(),
         HALResourceLinkBuilder.linkTo(GrappStoreLayoutResource.class).method("updateOuterOutline").pathArgs(grappStoreLayoutDTO.getId()).queryParams("polygon").withRel("outerOutline"),
         HALResourceLinkBuilder.linkTo(GrappStoreLayoutResource.class).method("updateInnerOutline").pathArgs(grappStoreLayoutDTO.getId()).queryParams("polygon").withRel("innerOutline"),
         HALResourceLinkBuilder.linkTo(GrappStoreLayoutResource.class).method("addFeature").pathArgs(grappStoreLayoutDTO.getId()).queryParams("polygon").withRel("addFeature"),
         HALResourceLinkBuilder.linkTo(GrappStoreLayoutResource.class).method("reshapeFeature").pathArgs(grappStoreLayoutDTO.getId()).queryParams("featureID", "polygon").withRel("reshapeFeature"),
         HALResourceLinkBuilder.linkTo(GrappStoreLayoutResource.class).method("removeFeature").pathArgs(grappStoreLayoutDTO.getId()).queryParams("featureID").withRel("removeFeature"),
         HALResourceLinkBuilder.linkTo(GrappStoreLayoutResource.class).method("addNode").pathArgs(grappStoreLayoutDTO.getId()).queryParams("location").withRel("addNode"),
         HALResourceLinkBuilder.linkTo(GrappStoreLayoutResource.class).method("moveNode").pathArgs(grappStoreLayoutDTO.getId()).queryParams("featureID", "location").withRel("moveNode"),
         HALResourceLinkBuilder.linkTo(GrappStoreLayoutResource.class).method("removeNode").pathArgs(grappStoreLayoutDTO.getId()).queryParams("featureID").withRel("removeNode")
      );
   }
}
