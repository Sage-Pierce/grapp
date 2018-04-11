package org.codegas.stores.restresource;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.codegas.commons.lang.spacial.GeoPoint;
import org.codegas.webservices.hal.api.HalLink;
import org.codegas.webservices.hal.api.HalRepresentation;
import org.codegas.webservices.hal.jaxrs.HalJsonResource;
import org.codegas.webservices.hal.jaxrs.HalResourceLinkBuilder;
import org.codegas.stores.service.api.StoreService;
import org.codegas.stores.service.dto.StoreDto;

@Path("/stores/{id}/")
public class StoreResource extends HalJsonResource {

    private final StoreService storeService;

    @Inject
    public StoreResource(StoreService storeService) {
        this.storeService = storeService;
    }

    @GET
    public Response get(@PathParam(value = "id") String id) {
        return buildHalResponse(asRepresentationOf(storeService.get(id)));
    }

    @PUT
    public Response update(@PathParam(value = "id") String id,
        @QueryParam(value = "name") String name,
        @QueryParam(value = "location") GeoPoint location) {
        return buildHalResponse(asRepresentationOf(storeService.update(id, name, location)));
    }

    @DELETE
    public Response delete(@PathParam(value = "id") String id) {
        storeService.delete(id);
        return Response.ok().build();
    }

    public static HalLink createRootLink(String rel) {
        return createSelfLinkBuilder().withRel(rel);
    }

    protected static HalRepresentation asRepresentationOf(StoreDto storeDto) {
        return halRepresentationFactory.createFor(storeDto).withLinks(createLinks(storeDto));
    }

    private static List<HalLink> createLinks(StoreDto storeDto) {
        return Collections.singletonList(createSelfLinkBuilder().pathArgs(storeDto.getId()).withSelfRel());
    }

    private static HalResourceLinkBuilder createSelfLinkBuilder() {
        return HalResourceLinkBuilder.linkTo(StoreResource.class).queryParams("name", "location");
    }
}
