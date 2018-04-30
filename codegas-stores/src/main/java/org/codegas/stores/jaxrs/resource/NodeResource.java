package org.codegas.stores.jaxrs.resource;

import java.util.Collections;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.codegas.webservice.hal.api.HalConfig;
import org.codegas.webservice.hal.api.HalLink;
import org.codegas.webservice.hal.api.HalRepresentation;
import org.codegas.webservice.hal.api.HalRepresentationFactory;
import org.codegas.webservice.hal.jaxrs.HalResourceLinkBuilder;
import org.codegas.stores.service.api.NodeService;
import org.codegas.stores.service.dto.NodeDto;

@Path("/nodes/{id}/")
@RolesAllowed("STORE_MANAGER")
public class NodeResource extends HalJsonResource {

    private final NodeService nodeService;

    @Inject
    public NodeResource(HalConfig halConfig, NodeService nodeService) {
        super(halConfig);
        this.nodeService = nodeService;
    }

    @GET
    public Response get(@PathParam("id") String id) {
        return buildHalResponse(asRepresentationOf(nodeService.get(id)));
    }

    @PUT
    public Response update(@PathParam("id") String id,
        @QueryParam("name") String name) {
        return buildHalResponse(asRepresentationOf(nodeService.update(id, name)));
    }

    @DELETE
    public Response delete(@PathParam("id") String id) {
        nodeService.delete(id);
        return Response.ok().build();
    }

    protected HalRepresentation asRepresentationOf(NodeDto nodeDto) {
        return asRepresentationOf(halRepresentationFactory, nodeDto);
    }

    protected static HalRepresentation asRepresentationOf(HalRepresentationFactory halRepresentationFactory, NodeDto nodeDto) {
        return halRepresentationFactory.createFor(nodeDto).withLinks(createLinks(nodeDto));
    }

    protected static HalLink createRootLink(String rel) {
        return createSelfLinkBuilder().withRel(rel);
    }

    private static List<HalLink> createLinks(NodeDto nodeDto) {
        return Collections.singletonList(createSelfLinkBuilder().pathArgs(nodeDto.getId()).withSelfRel());
    }

    private static HalResourceLinkBuilder createSelfLinkBuilder() {
        return HalResourceLinkBuilder.linkTo(NodeResource.class).queryParams("name");
    }
}
