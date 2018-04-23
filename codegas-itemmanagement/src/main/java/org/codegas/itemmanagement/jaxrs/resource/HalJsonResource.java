package org.codegas.itemmanagement.jaxrs.resource;

import javax.ws.rs.Produces;

import org.codegas.webservice.hal.api.HalRepresentationFactory;
import org.codegas.webservice.hal.jaxrs.HalResource;
import org.codegas.webservice.hal.representation.HalJsonRepresentationFactory;

@Produces(HalJsonRepresentationFactory.HAL_JSON)
public abstract class HalJsonResource extends HalResource {

    protected final HalRepresentationFactory halRepresentationFactory;

    public HalJsonResource() {
        this.halRepresentationFactory = new HalJsonRepresentationFactory();
    }
}
