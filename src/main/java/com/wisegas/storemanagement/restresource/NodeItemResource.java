package com.wisegas.storemanagement.restresource;

import com.wisegas.common.webservices.hal.api.HalLink;
import com.wisegas.common.webservices.hal.api.HalRepresentation;
import com.wisegas.common.webservices.jaxrs.hal.JaxrsHalJsonResource;
import com.wisegas.common.webservices.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import com.wisegas.storemanagement.service.api.NodeItemService;
import com.wisegas.storemanagement.service.dto.NodeItemDto;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Path("/nodeItems/{id}")
public class NodeItemResource extends JaxrsHalJsonResource {

   private final NodeItemService nodeItemService;

   @Inject
   public NodeItemResource(NodeItemService nodeItemService) {
      this.nodeItemService = nodeItemService;
   }

   @GET
   public Response get(@PathParam("id") final String id) {
      return buildHalResponse(asRepresentationOf(nodeItemService.get(id)));
   }

   @DELETE
   public Response delete(@PathParam("id") final String id) {
      nodeItemService.delete(id);
      return Response.ok().build();
   }

   public static HalLink createRootLink(String rel) {
      return createSelfLinkBuilder().withRel(rel);
   }

   protected static HalRepresentation asRepresentationOf(NodeItemDto nodeItemDto) {
      return halRepresentationFactory.createFor(nodeItemDto).withLinks(createLinks(nodeItemDto));
   }

   private static List<HalLink> createLinks(NodeItemDto nodeItemDto) {
      return Collections.singletonList(createSelfLinkBuilder().pathArgs(nodeItemDto.getId()).withSelfRel());
   }

   private static JaxrsHalResourceLinkBuilder createSelfLinkBuilder() {
      return JaxrsHalResourceLinkBuilder.linkTo(NodeItemResource.class);
   }
}
