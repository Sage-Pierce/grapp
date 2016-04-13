package com.wisegas.grapp.itemmanagement.restresource;

import com.wisegas.common.webserver.hal.api.HalLink;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalJsonResource;
import com.wisegas.common.webserver.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import com.wisegas.grapp.itemmanagement.service.api.NacsItemImportService;
import com.wisegas.grapp.itemmanagement.service.dto.GrappItemDTOO;

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
public class GrappItemImportResource extends JaxrsHalJsonResource {
   private static final String NACS = "NACS";

   private final NacsItemImportService nacsItemImportService;

   @Inject
   public GrappItemImportResource(NacsItemImportService nacsItemImportService) {
      this.nacsItemImportService = nacsItemImportService;
   }

   @PUT
   @Consumes(MediaType.TEXT_PLAIN)
   public Response importItems(@QueryParam("type") final String type,
                               final String csvData) {
      List<GrappItemDTOO> grappItems;
      if (NACS.equals(type)) {
         grappItems = nacsItemImportService.importCsvItems(csvData);
      }
      else {
         throw new UnsupportedOperationException("This Import Type is not supported: " + type);
      }

      return buildHalResponse(halRepresentationFactory.createForLinks(createLinks())
                                                      .withEmbeddeds("importedItems", grappItems.stream()
                                                                                                .map(GrappItemResource::asRepresentationOf)
                                                                                                .collect(Collectors.toList())));
   }

   public static HalLink createRootLink(String rel) {
      return createSelfLinkBuilder().withRel(rel);
   }

   private static List<HalLink> createLinks() {
      return Collections.singletonList(createSelfLinkBuilder().withSelfRel());
   }

   private static JaxrsHalResourceLinkBuilder createSelfLinkBuilder() {
      return JaxrsHalResourceLinkBuilder.linkTo(GrappItemImportResource.class).queryParams("type");
   }
}
