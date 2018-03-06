package org.codegas.common.webservices.jaxrs.hal;

import javax.ws.rs.Produces;

import org.codegas.common.webservices.hal.api.HalRepresentationFactory;
import org.codegas.common.webservices.hal.impl.HalJsonRepresentationFactory;

@Produces(HalJsonRepresentationFactory.HAL_JSON)
public abstract class JaxrsHalJsonResource extends JaxrsHalResource {

    protected static final HalRepresentationFactory halRepresentationFactory = new HalJsonRepresentationFactory();
}
