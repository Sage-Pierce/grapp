package org.codegas.pathgeneration.jaxrs.resource;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codegas.commons.lang.spacial.Point;
import org.codegas.pathgeneration.service.api.PathService;
import org.codegas.pathgeneration.service.dto.PathDto;
import org.codegas.pathgeneration.service.dto.PathPolygonsDto;
import org.codegas.pathgeneration.service.dto.WaypointsDto;
import org.codegas.webservice.hal.api.HalConfig;
import org.codegas.webservice.hal.api.HalLink;
import org.codegas.webservice.hal.api.HalRepresentation;
import org.codegas.webservice.hal.api.HalRepresentationFactory;
import org.codegas.webservice.hal.jaxrs.HalResource;
import org.codegas.webservice.hal.jaxrs.HalResourceLinkBuilder;
import org.codegas.webservice.hal.representation.HalJsonRepresentationFactory;

@Path("/pathGeneration/")
@Produces(HalJsonRepresentationFactory.HAL_JSON)
public class PathGenerationResource extends HalResource {

    private final HalRepresentationFactory halRepresentationFactory;

    private final PathService pathService;

    @Inject
    public PathGenerationResource(HalConfig halConfig, PathService pathService) {
        this.halRepresentationFactory = new HalJsonRepresentationFactory(halConfig);
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

    protected HalRepresentation asRepresentationOf(PathDto pathDto) {
        return halRepresentationFactory.createFor(pathDto).withLinks(createLinks());
    }

    private static List<HalLink> createLinks() {
        return Collections.singletonList(createSelfLinkBuilder().withSelfRel());
    }

    private static HalResourceLinkBuilder createSelfLinkBuilder() {
        return HalResourceLinkBuilder.linkTo(PathGenerationResource.class).queryParams("start", "finish", "waypoints");
    }
}
