package org.codegas.stores.restresource;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.codegas.webservices.hal.api.HalLink;
import org.codegas.webservices.hal.jaxrs.HalJsonResource;
import org.codegas.webservices.hal.jaxrs.HalResourceLinkBuilder;
import org.codegas.stores.service.api.StoreService;

@Path("/stores/")
public class StoresResource extends HalJsonResource {

    private final StoreService storeService;

    @Inject
    public StoresResource(StoreService storeService) {
        this.storeService = storeService;
    }

    @GET
    public Response get() {
        return buildHalResponse(halRepresentationFactory.createFor(storeService.get()).withLinks(createLinks()));
    }

    public static HalLink createRootLink(String rel) {
        return createSelfLinkBuilder().withRel(rel);
    }

    private static List<HalLink> createLinks() {
        return Collections.singletonList(createSelfLinkBuilder().withSelfRel());
    }

    private static HalResourceLinkBuilder createSelfLinkBuilder() {
        return HalResourceLinkBuilder.linkTo(StoresResource.class);
    }
}
