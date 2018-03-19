package org.codegas.itemmanagement.restresource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.codegas.commons.webservices.hal.api.HalLink;
import org.codegas.commons.webservices.hal.jaxrs.JaxrsHalJsonResource;
import org.codegas.commons.webservices.hal.jaxrs.JaxrsHalResourceLinkBuilder;
import org.codegas.itemmanagement.service.api.ItemService;

@Path("/generalItems/")
public class GeneralItemsResource extends JaxrsHalJsonResource {

    private final ItemService itemService;

    @Inject
    public GeneralItemsResource(ItemService itemService) {
        this.itemService = itemService;
    }

    @POST
    public Response createGeneralItem(@QueryParam("codeType") String codeType,
        @QueryParam("code") String code,
        @QueryParam("name") String name) {
        return buildHalResponse(ItemResource.asRepresentationOf(itemService.createGeneralItem(codeType, code, name)));
    }

    @GET
    public Response getGeneralItems() {
        return buildHalResponse(halRepresentationFactory.createForLinks(createLinks())
            .withEmbeddeds("generalItems", itemService.getGeneralItems().stream()
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
        return JaxrsHalResourceLinkBuilder.linkTo(GeneralItemsResource.class).queryParams("codeType", "code", "name");
    }
}
