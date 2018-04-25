package org.codegas.stores.jaxrs.resource;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.codegas.commons.lang.spacial.GeoPoint;
import org.codegas.commons.lang.value.Email;
import org.codegas.webservice.hal.api.HalConfig;
import org.codegas.webservice.hal.api.HalLink;
import org.codegas.webservice.hal.api.HalRepresentation;
import org.codegas.webservice.hal.api.HalRepresentationFactory;
import org.codegas.webservice.hal.jaxrs.HalResourceLinkBuilder;
import org.codegas.stores.service.api.StoreManagerService;
import org.codegas.stores.service.dto.StoreManagerDto;

@Path("/storeManagers/{id}/")
public class StoreManagerResource extends HalJsonResource {

    private final StoreManagerService storeManagerService;

    @Inject
    public StoreManagerResource(HalConfig halConfig, StoreManagerService storeManagerService) {
        super(halConfig);
        this.storeManagerService = storeManagerService;
    }

    @POST
    @Path("addStore")
    public Response addStore(@PathParam("id") Email email,
        @QueryParam("name") String name,
        @QueryParam("location") GeoPoint location) {
        return buildHalResponse(StoreResource.asRepresentationOf(halRepresentationFactory, storeManagerService.addStore(email, name, location)));
    }

    protected static HalRepresentation asRepresentationOf(HalRepresentationFactory halRepresentationFactory, StoreManagerDto storeManagerDto) {
        return halRepresentationFactory.createFor(storeManagerDto).withLinks(createLinks(storeManagerDto));
    }

    protected static HalLink createRootLink(String rel) {
        return createSelfLinkBuilder().withRel(rel);
    }

    private static List<HalLink> createLinks(StoreManagerDto storeManagerDto) {
        return Arrays.asList(
            createSelfLinkBuilder().pathArgs(storeManagerDto.getEmail()).withSelfRel(),
            HalResourceLinkBuilder.linkTo(StoreManagerResource.class).method("addStore").pathArgs(storeManagerDto.getEmail()).queryParams("name", "location")
                .withRel("addStore")
        );
    }

    private static HalResourceLinkBuilder createSelfLinkBuilder() {
        return HalResourceLinkBuilder.linkTo(StoreManagerResource.class);
    }
}
