package org.codegas.webservices.hal.jaxrs;

import javax.ws.rs.Produces;

import org.codegas.webservices.hal.api.HalRepresentationFactory;
import org.codegas.webservices.hal.representation.HalJsonRepresentationFactory;

@Produces(HalJsonRepresentationFactory.HAL_JSON)
public abstract class HalJsonResource extends HalResource {

    protected static final HalRepresentationFactory halRepresentationFactory = new HalJsonRepresentationFactory();
}
