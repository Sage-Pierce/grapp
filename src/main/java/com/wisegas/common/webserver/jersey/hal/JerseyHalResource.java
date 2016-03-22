package com.wisegas.common.webserver.jersey.hal;

import com.wisegas.common.webserver.hal.api.HALRepresentation;
import com.wisegas.common.webserver.hal.api.HalRepresentationFactory;
import com.wisegas.common.webserver.hal.impl.HalJsonRepresentationFactory;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Produces({HalJsonRepresentationFactory.HAL_JSON})
public abstract class JerseyHalResource {

   protected static HalRepresentationFactory halRepresentationFactory = new HalJsonRepresentationFactory();

   @Context
   protected UriInfo uriInfo;

   protected static Response buildHalResponse(HALRepresentation halRepresentation) {
      return Response.ok().entity(halRepresentation.toString()).build();
   }
}
