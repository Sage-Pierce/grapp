package org.codegas.stores.jaxrs.resource;

import java.util.Collections;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.codegas.webservice.hal.api.HalConfig;
import org.codegas.webservice.hal.api.HalLink;
import org.codegas.webservice.hal.api.HalRepresentation;
import org.codegas.webservice.hal.api.HalRepresentationFactory;
import org.codegas.webservice.hal.jaxrs.HalResourceLinkBuilder;
import org.codegas.stores.service.api.NodeItemService;
import org.codegas.stores.service.dto.NodeItemDto;

@Path("/nodeItems/{id}")
@RolesAllowed("STORE_MANAGER")
public class NodeItemResource extends HalJsonResource {

    private final NodeItemService nodeItemService;

    @Inject
    public NodeItemResource(HalConfig halConfig, NodeItemService nodeItemService) {
        super(halConfig);
        this.nodeItemService = nodeItemService;
    }

    @GET
    public Response get(@PathParam("id") String id) {
        return buildHalResponse(asRepresentationOf(nodeItemService.get(id)));
    }

    @DELETE
    public Response delete(@PathParam("id") String id) {
        nodeItemService.delete(id);
        return Response.ok().build();
    }

    protected HalRepresentation asRepresentationOf(NodeItemDto nodeItemDto) {
        return asRepresentationOf(halRepresentationFactory, nodeItemDto);
    }

    protected static HalRepresentation asRepresentationOf(HalRepresentationFactory halRepresentationFactory, NodeItemDto nodeItemDto) {
        return halRepresentationFactory.createFor(nodeItemDto).withLinks(createLinks(nodeItemDto));
    }

    protected static HalLink createRootLink(String rel) {
        return createSelfLinkBuilder().withRel(rel);
    }

    private static List<HalLink> createLinks(NodeItemDto nodeItemDto) {
        return Collections.singletonList(createSelfLinkBuilder().pathArgs(nodeItemDto.getId()).withSelfRel());
    }

    private static HalResourceLinkBuilder createSelfLinkBuilder() {
        return HalResourceLinkBuilder.linkTo(NodeItemResource.class);
    }
}
