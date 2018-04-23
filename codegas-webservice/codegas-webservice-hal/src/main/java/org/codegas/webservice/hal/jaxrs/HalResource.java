package org.codegas.webservice.hal.jaxrs;

import javax.ws.rs.core.Response;

import org.codegas.webservice.hal.api.HalRepresentation;

public abstract class HalResource {

    public static Response buildHalResponse(HalRepresentation halRepresentation) {
        return buildHalResponse(Response.Status.OK, halRepresentation);
    }

    public static Response buildHalResponse(Response.Status status, HalRepresentation halRepresentation) {
        return Response.status(status).entity(halRepresentation.toString()).build();
    }
}
