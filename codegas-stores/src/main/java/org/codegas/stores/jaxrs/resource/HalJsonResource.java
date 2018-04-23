package org.codegas.stores.jaxrs.resource;

import java.util.Collections;

import javax.ws.rs.Produces;

import org.codegas.webservice.hal.jaxrs.HalResource;
import org.codegas.webservice.hal.representation.HalJsonRepresentationFactory;

@Produces(HalJsonRepresentationFactory.HAL_JSON)
public abstract class HalJsonResource extends HalResource {

    public HalJsonResource() {
        super(new HalJsonRepresentationFactory(Collections.emptyMap()));
    }
}
