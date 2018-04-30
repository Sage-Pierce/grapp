package org.codegas.itemmanagement.jaxrs.resource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.codegas.webservice.hal.api.HalConfig;
import org.codegas.webservice.hal.api.HalLink;
import org.codegas.webservice.hal.jaxrs.HalResourceLinkBuilder;
import org.codegas.itemmanagement.service.api.ItemService;

@Path("/generalItems/")
@RolesAllowed("ADMIN")
public class GeneralItemsResource extends HalJsonResource {

    private final ItemService itemService;

    @Inject
    public GeneralItemsResource(HalConfig halConfig, ItemService itemService) {
        super(halConfig);
        this.itemService = itemService;
    }

    @POST
    public Response createGeneralItem(@QueryParam("codeType") String codeType,
        @QueryParam("code") String code,
        @QueryParam("name") String name) {
        return buildHalResponse(ItemResource.asRepresentationOf(halRepresentationFactory, itemService.createGeneralItem(codeType, code, name)));
    }

    @GET
    public Response getGeneralItems() {
        return buildHalResponse(halRepresentationFactory.createForLinks(createLinks())
            .withEmbeddeds("generalItems", itemService.getGeneralItems().stream()
                .map(itemDto -> ItemResource.asRepresentationOf(halRepresentationFactory, itemDto))
                .collect(Collectors.toList())));
    }

    protected static HalLink createRootLink(String rel) {
        return createSelfLinkBuilder().withRel(rel);
    }

    private static List<HalLink> createLinks() {
        return Collections.singletonList(createSelfLinkBuilder().withSelfRel());
    }

    private static HalResourceLinkBuilder createSelfLinkBuilder() {
        return HalResourceLinkBuilder.linkTo(GeneralItemsResource.class).queryParams("codeType", "code", "name");
    }
}
