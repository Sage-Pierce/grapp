package org.codegas.stores.restresource;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.codegas.commons.webservices.hal.api.HalLink;
import org.codegas.commons.webservices.jaxrs.hal.JaxrsHalJsonResource;
import org.codegas.commons.webservices.jaxrs.hal.JaxrsHalResourceLinkBuilder;
import org.codegas.stores.service.api.StoreService;

@Path("/stores/")
public class StoresResource extends JaxrsHalJsonResource {

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

    private static JaxrsHalResourceLinkBuilder createSelfLinkBuilder() {
        return JaxrsHalResourceLinkBuilder.linkTo(StoresResource.class);
    }
}
