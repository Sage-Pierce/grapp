package com.wisegas.common.webserver.jersey.hal;

import com.wisegas.common.webserver.hal.api.HalRepresentationFactory;
import com.wisegas.common.webserver.hal.impl.HalJsonRepresentationFactory;

import javax.ws.rs.Produces;

@Produces({HalJsonRepresentationFactory.HAL_JSON})
public abstract class JerseyHalJsonResource extends JerseyHalResource {
   protected static final HalRepresentationFactory halRepresentationFactory = new HalJsonRepresentationFactory();
}
