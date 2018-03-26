package org.codegas.stores.restresource;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.codegas.webservices.hal.api.HalLink;
import org.codegas.webservices.hal.jaxrs.HalJsonResource;
import org.codegas.webservices.hal.jaxrs.HalResourceLinkBuilder;

@Path("/stores/root/")
public class RootResource extends HalJsonResource {

    @GET
    public Response get() {
        return buildHalResponse(halRepresentationFactory.createForLinks(createLinks()));
    }

    private static List<HalLink> createLinks() {
        return Arrays.asList(
            HalResourceLinkBuilder.linkTo(RootResource.class).withSelfRel(),
            StoreManagersResource.createRootLink("storeManagers"),
            StoreManagerResource.createRootLink("storeManager"),
            StoresResource.createRootLink("stores"),
            StoreResource.createRootLink("store"),
            StoreLayoutResource.createRootLink("storeLayout"),
            FeatureResource.createRootLink("feature"),
            NodeResource.createRootLink("node"),
            NodeItemResource.createRootLink("nodeItem"),
            ShoppingLayoutResource.createRootLink("shoppingLayout")
        );
    }
}
