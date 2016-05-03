package com.wisegas.storemanagement.restresource;

import com.wisegas.common.webservices.hal.api.HalLink;
import com.wisegas.common.webservices.hal.api.HalRepresentation;
import com.wisegas.common.webservices.jaxrs.hal.JaxrsHalJsonResource;
import com.wisegas.common.webservices.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import com.wisegas.storemanagement.service.api.NodeService;
import com.wisegas.storemanagement.service.dto.NodeDto;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Path("/nodes/{id}/")
public class NodeResource extends JaxrsHalJsonResource {

   private final NodeService nodeService;

   @Inject
   public NodeResource(NodeService nodeService) {
      this.nodeService = nodeService;
   }

   @GET
   public Response get(@PathParam("id") final String id) {
      return buildHalResponse(asRepresentationOf(nodeService.get(id)));
   }

   @PUT
   public Response update(@PathParam("id") final String id,
                          @QueryParam("name") final String name) {
      NodeDto nodeDto = nodeService.update(id, name);
      return buildHalResponse(asRepresentationOf(nodeDto));
   }

   @DELETE
   public Response delete(@PathParam("id") final String id) {
      nodeService.delete(id);
      return Response.ok().build();
   }

   public static HalLink createRootLink(String rel) {
      return createSelfLinkBuilder().withRel(rel);
   }

   protected static HalRepresentation asRepresentationOf(NodeDto nodeDto) {
      return halRepresentationFactory.createFor(nodeDto).withLinks(createLinks(nodeDto));
   }

   private static List<HalLink> createLinks(NodeDto nodeDto) {
      return Collections.singletonList(createSelfLinkBuilder().pathArgs(nodeDto.getId()).withSelfRel());
   }

   private static JaxrsHalResourceLinkBuilder createSelfLinkBuilder() {
      return JaxrsHalResourceLinkBuilder.linkTo(NodeResource.class).queryParams("name");
   }
}
