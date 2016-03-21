package com.wisegas.grapp.restresource;

import com.wisegas.common.webserver.hal.HALResource;
import com.wisegas.common.webserver.hal.HALResourceLinkBuilder;
import com.wisegas.common.webserver.hal.api.HALLink;
import com.wisegas.grapp.service.api.GrappItemService;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/items/")
public class GrappItemsResource extends HALResource {

   private final GrappItemService grappItemService;

   @Inject
   public GrappItemsResource(GrappItemService grappItemService) {
      this.grappItemService = grappItemService;
   }

   @POST
   public Response create(@QueryParam("superItemId") final String superItemId,
                          @QueryParam("name") final String name) {
      return buildHALResponse(GrappItemResource.asRepresentationOf(grappItemService.createSubItem(superItemId, name)));
   }

   protected static HALLink createRootLink(String rel) {
      return createSelfLinkBuilder().withRel(rel);
   }

   private static HALResourceLinkBuilder createSelfLinkBuilder() {
      return HALResourceLinkBuilder.linkTo(GrappItemsResource.class).queryParams("superItemId", "name");
   }

}
