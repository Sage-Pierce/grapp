package org.codegas.stores.jaxrs.resource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
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
import org.codegas.webservice.hal.api.HalConfig;
import org.codegas.webservice.hal.api.HalLink;
import org.codegas.webservice.hal.api.HalRepresentation;
import org.codegas.webservice.hal.api.HalRepresentationFactory;
import org.codegas.webservice.hal.jaxrs.HalResourceLinkBuilder;
import org.codegas.stores.service.api.StoreLayoutService;
import org.codegas.stores.service.dto.NodeDto;
import org.codegas.stores.service.dto.NodeItemDto;
import org.codegas.stores.service.dto.StoreLayoutDto;
import org.codegas.stores.service.dto.StoreLayoutUpdateDto;

@Path("/storeLayouts/{id}/")
@RolesAllowed("STORE_MANAGER")
public class StoreLayoutResource extends HalJsonResource {

    private final StoreLayoutService storeLayoutService;

    @Inject
    public StoreLayoutResource(HalConfig halConfig, StoreLayoutService storeLayoutService) {
        super(halConfig);
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
        return buildHalResponse(FeatureResource.asRepresentationOf(halRepresentationFactory, storeLayoutService.addFeature(id, polygon)));
    }

    @PUT
    @Path("features/reshape")
    public Response reshapeFeature(@PathParam("id") String id,
        @QueryParam("featureId") String featureId,
        @QueryParam("polygon") GeoPolygon polygon) {
        return buildHalResponse(FeatureResource.asRepresentationOf(halRepresentationFactory, storeLayoutService.reshapeFeature(id, featureId, polygon)));
    }

    @POST
    @Path("nodes")
    public Response addNode(@PathParam("id") String id,
        @QueryParam("type") String type,
        @QueryParam("location") GeoPoint location) {
        StoreLayoutUpdateDto<NodeDto> result = storeLayoutService.addNode(id, type, location);
        return buildHalResponse(NodeResource.asRepresentationOf(halRepresentationFactory, result.getTarget())
            .withEmbeddeds("affectedNodes", result.getAffectedNodes().stream()
                .map(nodeDto -> NodeResource.asRepresentationOf(halRepresentationFactory, nodeDto))
                .collect(Collectors.toList())));
    }

    @PUT
    @Path("nodes/move")
    public Response moveNode(@PathParam("id") String id,
        @QueryParam("nodeId") String nodeId,
        @QueryParam("location") GeoPoint location) {
        return buildHalResponse(NodeResource.asRepresentationOf(halRepresentationFactory, storeLayoutService.moveNode(id, nodeId, location)));
    }

    @POST
    @Path("nodes/items")
    public Response addNodeItem(@PathParam("id") String id,
        @QueryParam("nodeId") String nodeId,
        @QueryParam("item") CodeName item) {
        StoreLayoutUpdateDto<NodeItemDto> result = storeLayoutService.addNodeItem(id, nodeId, item);
        return buildHalResponse(NodeItemResource.asRepresentationOf(halRepresentationFactory, result.getTarget())
            .withEmbeddeds("affectedNodes", result.getAffectedNodes().stream()
                .map(nodeDto -> NodeResource.asRepresentationOf(halRepresentationFactory, nodeDto))
                .collect(Collectors.toList())));
    }

    protected HalRepresentation asRepresentationOf(StoreLayoutDto storeLayoutDto) {
        return asRepresentationOf(halRepresentationFactory, storeLayoutDto);
    }

    protected static HalRepresentation asRepresentationOf(HalRepresentationFactory halRepresentationFactory, StoreLayoutDto storeLayoutDto) {
        return halRepresentationFactory.createFor(storeLayoutDto).withLinks(createLinks(storeLayoutDto));
    }

    protected static HalLink createRootLink(String rel) {
        return createSelfLinkBuilder().withRel(rel);
    }

    private static List<HalLink> createLinks(StoreLayoutDto storeLayoutDto) {
        return Arrays.asList(
            createSelfLinkBuilder().pathArgs(storeLayoutDto.getId()).withSelfRel(),
            HalResourceLinkBuilder.linkTo(StoreLayoutResource.class).method("updateOuterOutline").pathArgs(storeLayoutDto.getId()).queryParams("polygon")
                .withRel("outerOutline"),
            HalResourceLinkBuilder.linkTo(StoreLayoutResource.class).method("updateInnerOutline").pathArgs(storeLayoutDto.getId()).queryParams("polygon")
                .withRel("innerOutline"),
            HalResourceLinkBuilder.linkTo(StoreLayoutResource.class).method("addFeature").pathArgs(storeLayoutDto.getId()).queryParams("polygon").withRel("addFeature"),
            HalResourceLinkBuilder.linkTo(StoreLayoutResource.class).method("reshapeFeature").pathArgs(storeLayoutDto.getId()).queryParams("featureId", "polygon")
                .withRel("reshapeFeature"),
            HalResourceLinkBuilder.linkTo(StoreLayoutResource.class).method("addNode").pathArgs(storeLayoutDto.getId()).queryParams("type", "location").withRel("addNode"),
            HalResourceLinkBuilder.linkTo(StoreLayoutResource.class).method("moveNode").pathArgs(storeLayoutDto.getId()).queryParams("nodeId", "location").withRel("moveNode"),
            HalResourceLinkBuilder.linkTo(StoreLayoutResource.class).method("addNodeItem").pathArgs(storeLayoutDto.getId()).queryParams("nodeId", "item")
                .withRel("addNodeItem")
        );
    }

    private static HalResourceLinkBuilder createSelfLinkBuilder() {
        return HalResourceLinkBuilder.linkTo(StoreLayoutResource.class);
    }
}
