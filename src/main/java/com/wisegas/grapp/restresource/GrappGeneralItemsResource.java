package com.wisegas.grapp.restresource;

import com.wisegas.common.webserver.hal.HALResource;
import com.wisegas.common.webserver.hal.HALResourceLinkBuilder;
import com.wisegas.common.webserver.hal.api.HALLink;
import com.wisegas.grapp.service.api.GrappItemService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Path("/generalItems/")
public class GrappGeneralItemsResource extends HALResource {

   private final GrappItemService grappItemService;

   @Inject
   public GrappGeneralItemsResource(GrappItemService grappItemService) {
      this.grappItemService = grappItemService;
   }

   @POST
   public Response create(@QueryParam("name") final String name) {
      return buildHALResponse(GrappItemResource.asRepresentationOf(grappItemService.createGeneralItem(name)));
   }

   @GET
   public Response get() {
      return buildHALResponse(halRepresentationFactory.createForLinks(createLinks())
                                                      .withEmbeddeds("generalItems", grappItemService.getGeneralItems().stream()
                                                                                                     .map(GrappItemResource::asRepresentationOf)
                                                                                                     .collect(Collectors.toList())));
   }

   protected static HALLink createRootLink(String rel) {
      return createSelfLinkBuilder().withRel(rel);
   }

   private static List<HALLink> createLinks() {
      return Collections.singletonList(createSelfLinkBuilder().withSelfRel());
   }

   private static HALResourceLinkBuilder createSelfLinkBuilder() {
      return HALResourceLinkBuilder.linkTo(GrappGeneralItemsResource.class).queryParams("name");
   }
}
