package org.codegas.stores.restresource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.codegas.commons.lang.spacial.GeoPoint;
import org.codegas.commons.lang.spacial.GeoPolygon;
import org.codegas.commons.lang.value.CodeName;
import org.codegas.commons.webservices.hal.api.HalLink;
import org.codegas.commons.webservices.hal.api.HalRepresentation;
import org.codegas.commons.webservices.jaxrs.hal.JaxrsHalJsonResource;
import org.codegas.commons.webservices.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import org.codegas.stores.service.api.StoreLayoutService;
import org.codegas.stores.service.dto.NodeDto;
import org.codegas.stores.service.dto.NodeItemDto;
import org.codegas.stores.service.dto.StoreLayoutDto;
import org.codegas.stores.service.dto.StoreLayoutUpdateDto;

@Path("/storeLayouts/{id}/")
public class StoreLayoutResource extends JaxrsHalJsonResource {

    private final StoreLayoutService storeLayoutService;

    @Inject
    public StoreLayoutResource(StoreLayoutService storeLayoutService) {
        this.storeLayoutService = storeLayoutService;
    }

    @GET
    public Response get(@PathParam("id") String id) {
        return buildHalResponse(asRepresentationOf(storeLayoutService.get(id)));
    }

    @PUT
    @Path("outerOutline")
    public Response updateOuterOutline(@PathParam("id") String id,
        @QueryParam("polygon") GeoPolygon polygon) {
        return buildHalResponse(asRepresentationOf(storeLayoutService.updateOuterOutline(id, polygon)));
    }

    @PUT
    @Path("innerOutline")
    public Response updateInnerOutline(@PathParam("id") String id,
        @QueryParam("polygon") GeoPolygon polygon) {
        return buildHalResponse(asRepresentationOf(storeLayoutService.updateInnerOutline(id, polygon)));
    }

    @POST
    @Path("features")
    public Response addFeature(@PathParam("id") String id,
        @QueryParam("polygon") GeoPolygon polygon) {
        return buildHalResponse(FeatureResource.asRepresentationOf(storeLayoutService.addFeature(id, polygon)));
    }

    @PUT
    @Path("features/reshape")
    public Response reshapeFeature(@PathParam("id") String id,
        @QueryParam("featureId") String featureId,
        @QueryParam("polygon") GeoPolygon polygon) {
        return buildHalResponse(FeatureResource.asRepresentationOf(storeLayoutService.reshapeFeature(id, featureId, polygon)));
    }

    @POST
    @Path("nodes")
    public Response addNode(@PathParam("id") String id,
        @QueryParam("type") String type,
        @QueryParam("location") GeoPoint location) {
        StoreLayoutUpdateDto<NodeDto> result = storeLayoutService.addNode(id, type, location);
        return buildHalResponse(NodeResource.asRepresentationOf(result.getTarget())
            .withEmbeddeds("affectedNodes", result.getAffectedNodes().stream()
                .map(NodeResource::asRepresentationOf)
                .collect(Collectors.toList())));
    }

    @PUT
    @Path("nodes/move")
    public Response moveNode(@PathParam("id") String id,
        @QueryParam("nodeId") String nodeId,
        @QueryParam("location") GeoPoint location) {
        return buildHalResponse(NodeResource.asRepresentationOf(storeLayoutService.moveNode(id, nodeId, location)));
    }

    @POST
    @Path("nodes/items")
    public Response addNodeItem(@PathParam("id") String id,
        @QueryParam("nodeId") String nodeId,
        @QueryParam("item") CodeName item) {
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
            JaxrsHalResourceLinkBuilder.linkTo(StoreLayoutResource.class).method("updateOuterOutline").pathArgs(storeLayoutDto.getId()).queryParams("polygon")
                .withRel("outerOutline"),
            JaxrsHalResourceLinkBuilder.linkTo(StoreLayoutResource.class).method("updateInnerOutline").pathArgs(storeLayoutDto.getId()).queryParams("polygon")
                .withRel("innerOutline"),
            JaxrsHalResourceLinkBuilder.linkTo(StoreLayoutResource.class).method("addFeature").pathArgs(storeLayoutDto.getId()).queryParams("polygon").withRel("addFeature"),
            JaxrsHalResourceLinkBuilder.linkTo(StoreLayoutResource.class).method("reshapeFeature").pathArgs(storeLayoutDto.getId()).queryParams("featureId", "polygon")
                .withRel("reshapeFeature"),
            JaxrsHalResourceLinkBuilder.linkTo(StoreLayoutResource.class).method("addNode").pathArgs(storeLayoutDto.getId()).queryParams("type", "location").withRel("addNode"),
            JaxrsHalResourceLinkBuilder.linkTo(StoreLayoutResource.class).method("moveNode").pathArgs(storeLayoutDto.getId()).queryParams("nodeId", "location").withRel("moveNode"),
            JaxrsHalResourceLinkBuilder.linkTo(StoreLayoutResource.class).method("addNodeItem").pathArgs(storeLayoutDto.getId()).queryParams("nodeId", "item")
                .withRel("addNodeItem")
        );
    }

    private static JaxrsHalResourceLinkBuilder createSelfLinkBuilder() {
        return JaxrsHalResourceLinkBuilder.linkTo(StoreLayoutResource.class);
    }
}
