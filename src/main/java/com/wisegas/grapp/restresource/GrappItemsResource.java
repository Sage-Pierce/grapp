package com.wisegas.grapp.restresource;

import com.wisegas.common.webserver.hal.apiv2.HalLink;
import com.wisegas.common.webserver.jersey.hal.JerseyHalJsonResource;
import com.wisegas.common.webserver.jersey.hal.JerseyHalResourceLinkBuilder;
import com.wisegas.grapp.service.api.GrappItemService;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/items/")
public class GrappItemsResource extends JerseyHalJsonResource {

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

   protected static HalLink createRootLink(String rel) {
      return createSelfLinkBuilder().withRel(rel);
   }

   private static JerseyHalResourceLinkBuilder createSelfLinkBuilder() {
      return JerseyHalResourceLinkBuilder.linkTo(GrappItemsResource.class).queryParams("superItemId", "name");
   }

}
