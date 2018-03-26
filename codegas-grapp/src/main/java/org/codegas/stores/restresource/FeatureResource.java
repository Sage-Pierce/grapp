package org.codegas.stores.restresource;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.codegas.commons.webservices.hal.api.HalLink;
import org.codegas.commons.webservices.hal.api.HalRepresentation;
import org.codegas.commons.webservices.hal.jaxrs.HalJsonResource;
import org.codegas.commons.webservices.hal.jaxrs.HalResourceLinkBuilder;
import org.codegas.stores.service.api.FeatureService;
import org.codegas.stores.service.dto.FeatureDto;

@Path("/features/{id}/")
public class FeatureResource extends HalJsonResource {

    private final FeatureService featureService;

    @Inject
    public FeatureResource(FeatureService featureService) {
        this.featureService = featureService;
    }

    @GET
    public Response get(@PathParam("id") String id) {
        return buildHalResponse(asRepresentationOf(featureService.get(id)));
    }

    @DELETE
    public Response delete(@PathParam("id") String id) {
        featureService.delete(id);
        return Response.ok().build();
    }

    public static HalLink createRootLink(String rel) {
        return createSelfLinkBuilder().withRel(rel);
    }

    protected static HalRepresentation asRepresentationOf(FeatureDto featureDto) {
        return halRepresentationFactory.createFor(featureDto).withLinks(createLinks(featureDto));
    }

    private static List<HalLink> createLinks(FeatureDto featureDto) {
        return Collections.singletonList(createSelfLinkBuilder().pathArgs(featureDto.getId()).withSelfRel());
    }

    private static HalResourceLinkBuilder createSelfLinkBuilder() {
        return HalResourceLinkBuilder.linkTo(FeatureResource.class);
    }
}
