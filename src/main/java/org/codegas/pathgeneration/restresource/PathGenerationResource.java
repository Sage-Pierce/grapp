package org.codegas.pathgeneration.restresource;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codegas.common.lang.spacial.Point;
import org.codegas.common.webservices.hal.api.HalLink;
import org.codegas.common.webservices.hal.api.HalRepresentation;
import org.codegas.common.webservices.jaxrs.hal.JaxrsHalJsonResource;
import org.codegas.common.webservices.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import org.codegas.pathgeneration.service.api.PathService;
import org.codegas.pathgeneration.service.dto.PathDto;
import org.codegas.pathgeneration.service.dto.PathPolygonsDto;
import org.codegas.pathgeneration.service.dto.WaypointsDto;

@Path("/pathGeneration/")
public class PathGenerationResource extends JaxrsHalJsonResource {

    private final PathService pathService;

    @Inject
    public PathGenerationResource(PathService pathService) {
        this.pathService = pathService;
    }

    @GET
    public Response get() {
        return buildHalResponse(halRepresentationFactory.createForLinks(createLinks()));
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response generatePath(@QueryParam("start") Point start,
        @QueryParam("finish") Point finish,
        @QueryParam("waypoints") WaypointsDto waypoints,
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
