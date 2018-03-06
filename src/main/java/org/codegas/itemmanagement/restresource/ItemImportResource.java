package org.codegas.itemmanagement.restresource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codegas.commons.webservices.hal.api.HalLink;
import org.codegas.commons.webservices.jaxrs.hal.JaxrsHalJsonResource;
import org.codegas.commons.webservices.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import org.codegas.itemmanagement.service.api.NacsItemImportService;
import org.codegas.itemmanagement.service.dto.ItemDto;

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
    public Response importItems(@QueryParam("type") String type,
        String csvData) {
        List<ItemDto> items;
        if (NACS.equals(type)) {
            items = nacsItemImportService.importItems(csvData);
        } else {
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
