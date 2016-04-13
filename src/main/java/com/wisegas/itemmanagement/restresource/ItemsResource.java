package com.wisegas.itemmanagement.restresource;

import com.wisegas.common.webserver.hal.api.HalLink;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalJsonResource;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import com.wisegas.itemmanagement.service.api.ItemService;

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
public class ItemsResource extends JaxrsHalJsonResource {

   private final ItemService itemService;

   @Inject
   public ItemsResource(ItemService itemService) {
      this.itemService = itemService;
   }

   @POST
   public Response create(@QueryParam("superItemId") final String superItemId,
                          @QueryParam("codeType") final String codeType,
                          @QueryParam("code") final String code,
                          @QueryParam("name") final String name) {
      return buildHalResponse(ItemResource.asRepresentationOf(itemService.createSubItem(superItemId, codeType, code, name)));
   }

   @GET
   public Response get() {
      return buildHalResponse(halRepresentationFactory.createForLinks(createLinks())
                                                      .withEmbeddeds("items", itemService.getAll().stream()
                                                                                         .map(ItemResource::asRepresentationOf)
                                                                                         .collect(Collectors.toList())));
   }

   public static HalLink createRootLink(String rel) {
      return createSelfLinkBuilder().withRel(rel);
   }

   private static List<HalLink> createLinks() {
      return Collections.singletonList(createSelfLinkBuilder().withSelfRel());
   }

   private static JaxrsHalResourceLinkBuilder createSelfLinkBuilder() {
      return JaxrsHalResourceLinkBuilder.linkTo(ItemsResource.class).queryParams("superItemId", "codeType", "code", "name");
   }
}
