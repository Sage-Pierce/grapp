package org.codegas.stores.jaxrs.resource;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Produces;

import org.codegas.webservice.hal.api.HalConfig;
import org.codegas.webservice.hal.api.HalRepresentationFactory;
import org.codegas.webservice.hal.jaxrs.HalResource;
import org.codegas.webservice.hal.representation.HalJsonRepresentationFactory;

@Produces(HalJsonRepresentationFactory.HAL_JSON)
@RolesAllowed("STORE_MANAGER")
public abstract class HalJsonResource extends HalResource {

    protected final HalRepresentationFactory halRepresentationFactory;

    public HalJsonResource(HalConfig halConfig) {
        this.halRepresentationFactory = new HalJsonRepresentationFactory(halConfig);
    }
}
