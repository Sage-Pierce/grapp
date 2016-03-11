package com.wisegas.common.webserver.hal;

import com.wisegas.common.webserver.hal.api.HALRepresentation;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Produces({HALRepresentationFactory.HAL_JSON})
public abstract class HALResource {

   protected static HALRepresentationFactory halRepresentationFactory = new HALRepresentationFactory();

   @Context
   protected UriInfo uriInfo;

   protected static Response buildHALResponse(HALRepresentation halRepresentation) {
      return Response.ok().entity(halRepresentation.toString()).build();
   }
}
