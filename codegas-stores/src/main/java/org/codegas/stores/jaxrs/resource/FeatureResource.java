package org.codegas.stores.jaxrs.resource;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.codegas.webservice.hal.api.HalLink;
import org.codegas.webservice.hal.api.HalRepresentation;
import org.codegas.webservice.hal.api.HalRepresentationFactory;
import org.codegas.webservice.hal.jaxrs.HalResourceLinkBuilder;
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

    protected HalRepresentation asRepresentationOf(FeatureDto featureDto) {
        return asRepresentationOf(halRepresentationFactory, featureDto);
    }

    protected static HalRepresentation asRepresentationOf(HalRepresentationFactory halRepresentationFactory, FeatureDto featureDto) {
        return halRepresentationFactory.createFor(featureDto).withLinks(createLinks(featureDto));
    }

    protected static HalLink createRootLink(String rel) {
        return createSelfLinkBuilder().withRel(rel);
    }

    private static List<HalLink> createLinks(FeatureDto featureDto) {
        return Collections.singletonList(createSelfLinkBuilder().pathArgs(featureDto.getId()).withSelfRel());
    }

    private static HalResourceLinkBuilder createSelfLinkBuilder() {
        return HalResourceLinkBuilder.linkTo(FeatureResource.class);
    }
}
