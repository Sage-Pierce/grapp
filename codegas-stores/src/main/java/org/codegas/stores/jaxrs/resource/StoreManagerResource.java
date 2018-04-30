package org.codegas.stores.jaxrs.resource;

import java.util.Arrays;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.codegas.commons.lang.spacial.GeoPoint;
import org.codegas.commons.lang.value.PrincipalName;
import org.codegas.stores.service.api.StoreManagerService;
import org.codegas.stores.service.dto.StoreDto;
import org.codegas.stores.service.dto.StoreManagerDto;
import org.codegas.webservice.hal.api.HalConfig;
import org.codegas.webservice.hal.api.HalLink;
import org.codegas.webservice.hal.api.HalRepresentation;
import org.codegas.webservice.hal.api.HalRepresentationFactory;
import org.codegas.webservice.hal.jaxrs.HalResourceLinkBuilder;

@Path("/storeManagers/")
@RolesAllowed({ "ADMIN", "STORE_MANAGER" })
public class StoreManagerResource extends HalJsonResource {

    private final StoreManagerService storeManagerService;

    @Inject
    public StoreManagerResource(HalConfig halConfig, StoreManagerService storeManagerService) {
        super(halConfig);
        this.storeManagerService = storeManagerService;
    }

    @POST
    public Response createStoreManager(@Context SecurityContext securityContext) {
        return buildHalResponse(asRepresentationOf(storeManagerService.create(PrincipalName.fromString(securityContext.getUserPrincipal().getName()))));
    }

    @GET
    public Response getStoreManager(@Context SecurityContext securityContext) {
        return storeManagerService.find(PrincipalName.fromString(securityContext.getUserPrincipal().getName()))
            .map(storeManagerDto -> buildHalResponse(asRepresentationOf(storeManagerDto)))
            .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Path("addStore")
    public Response addStore(@Context SecurityContext securityContext, @QueryParam("name") String name, @QueryParam("location") GeoPoint location) {
        PrincipalName managerName = PrincipalName.fromString(securityContext.getUserPrincipal().getName());
        StoreDto storeDto = storeManagerService.addStore(managerName, name, location);
        return buildHalResponse(StoreResource.asRepresentationOf(halRepresentationFactory, storeDto));
    }

    protected HalRepresentation asRepresentationOf(StoreManagerDto storeManagerDto) {
        return asRepresentationOf(halRepresentationFactory, storeManagerDto);
    }

    protected static HalRepresentation asRepresentationOf(HalRepresentationFactory halRepresentationFactory, StoreManagerDto storeManagerDto) {
        return halRepresentationFactory.createFor(storeManagerDto).withLinks(createLinks(storeManagerDto));
    }

    protected static HalLink createRootLink(String rel) {
        return createSelfLinkBuilder().withRel(rel);
    }

    private static List<HalLink> createLinks(StoreManagerDto storeManagerDto) {
        return Arrays.asList(
            createSelfLinkBuilder().pathArgs(storeManagerDto.getName()).withSelfRel(),
            HalResourceLinkBuilder.linkTo(StoreManagerResource.class).method("addStore").pathArgs(storeManagerDto.getName()).queryParams("name", "location")
                .withRel("addStore")
        );
    }

    private static HalResourceLinkBuilder createSelfLinkBuilder() {
        return HalResourceLinkBuilder.linkTo(StoreManagerResource.class);
    }
}
