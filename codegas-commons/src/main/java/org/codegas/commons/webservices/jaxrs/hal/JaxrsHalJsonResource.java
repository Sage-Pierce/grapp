package org.codegas.commons.webservices.jaxrs.hal;

import javax.ws.rs.Produces;

import org.codegas.commons.webservices.hal.api.HalRepresentationFactory;
import org.codegas.commons.webservices.hal.impl.HalJsonRepresentationFactory;

@Produces(HalJsonRepresentationFactory.HAL_JSON)
public abstract class JaxrsHalJsonResource extends JaxrsHalResource {

    protected static final HalRepresentationFactory halRepresentationFactory = new HalJsonRepresentationFactory();
}
