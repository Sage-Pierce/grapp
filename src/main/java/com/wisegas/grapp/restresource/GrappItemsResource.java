package com.wisegas.grapp.restresource;

import com.wisegas.common.webserver.hal.api.HalLink;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalJsonResource;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalResourceLinkBuilder;
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

@Path("/items/")
public class GrappItemsResource extends JaxrsHalJsonResource {

   private final GrappItemService grappItemService;

   @Inject
   public GrappItemsResource(GrappItemService grappItemService) {
      this.grappItemService = grappItemService;
   }

   @POST
   public Response create(@QueryParam("superItemId") final String superItemId,
                          @QueryParam("name") final String name) {
      return buildHalResponse(GrappItemResource.asRepresentationOf(grappItemService.createSubItem(superItemId, name)));
   }

   @GET
   public Response get() {
      return buildHalResponse(halRepresentationFactory.createForLinks(createLinks())
                                                      .withEmbeddeds("items", grappItemService.getAll().stream()
                                                                                              .map(GrappItemResource::asRepresentationOf)
                                                                                              .collect(Collectors.toList())));
   }

   protected static HalLink createRootLink(String rel) {
      return createSelfLinkBuilder().withRel(rel);
   }

   private static List<HalLink> createLinks() {
      return Collections.singletonList(createSelfLinkBuilder().withSelfRel());
   }

   private static JaxrsHalResourceLinkBuilder createSelfLinkBuilder() {
      return JaxrsHalResourceLinkBuilder.linkTo(GrappItemsResource.class).queryParams("superItemId", "name");
   }
}
