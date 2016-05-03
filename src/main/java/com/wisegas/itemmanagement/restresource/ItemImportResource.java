package com.wisegas.itemmanagement.restresource;

import com.wisegas.common.webservices.hal.api.HalLink;
import com.wisegas.common.webservices.jaxrs.hal.JaxrsHalJsonResource;
import com.wisegas.common.webservices.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import com.wisegas.itemmanagement.service.api.NacsItemImportService;
import com.wisegas.itemmanagement.service.dto.ItemDto;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Path("/items/import")
public class ItemImportResource extends JaxrsHalJsonResource {
   private static final String NACS = "NACS";

   private final NacsItemImportService nacsItemImportService;

   @Inject
   public ItemImportResource(NacsItemImportService nacsItemImportService) {
      this.nacsItemImportService = nacsItemImportService;
   }

   @PUT
   @Consumes(MediaType.TEXT_PLAIN)
   public Response importItems(@QueryParam("type") final String type,
                               final String csvData) {
      List<ItemDto> items;
      if (NACS.equals(type)) {
         items = nacsItemImportService.importCsvItems(csvData);
      }
      else {
         throw new UnsupportedOperationException("This Import Type is not supported: " + type);
      }

      return buildHalResponse(halRepresentationFactory.createForLinks(createLinks())
                                                      .withEmbeddeds("importedItems", items.stream()
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
      return JaxrsHalResourceLinkBuilder.linkTo(ItemImportResource.class).queryParams("type");
   }
}
