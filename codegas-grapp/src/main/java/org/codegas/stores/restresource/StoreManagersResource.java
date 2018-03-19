package org.codegas.stores.restresource;

import javax.inject.Inject;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.codegas.commons.lang.value.Email;
import org.codegas.commons.webservices.hal.api.HalLink;
import org.codegas.commons.webservices.hal.jaxrs.JaxrsHalJsonResource;
import org.codegas.commons.webservices.hal.jaxrs.JaxrsHalResourceLinkBuilder;
import org.codegas.stores.service.api.StoreManagerService;

@Path("/storeManagers/")
public class StoreManagersResource extends JaxrsHalJsonResource {

    private final StoreManagerService storeManagerService;

    @Inject
    public StoreManagersResource(StoreManagerService storeManagerService) {
        this.storeManagerService = storeManagerService;
    }

    @PUT
    public Response loadByEmail(@QueryParam("email") Email email) {
        return buildHalResponse(StoreManagerResource.asRepresentationOf(storeManagerService.loadByEmail(email)));
    }

    public static HalLink createRootLink(String rel) {
        return createSelfLinkBuilder().withRel(rel);
    }

    private static JaxrsHalResourceLinkBuilder createSelfLinkBuilder() {
        return JaxrsHalResourceLinkBuilder.linkTo(StoreManagersResource.class).queryParams("email");
    }
}
