package org.codegas.webservice.hal.jaxrs;

import javax.ws.rs.core.Response;

import org.codegas.webservice.hal.api.HalRepresentation;
import org.codegas.webservice.hal.api.HalRepresentationFactory;

public abstract class HalResource {

    protected final HalRepresentationFactory halRepresentationFactory;

    public HalResource(HalRepresentationFactory halRepresentationFactory) {
        this.halRepresentationFactory = halRepresentationFactory;
    }

    protected static Response buildHalResponse(HalRepresentation halRepresentation) {
        return buildHalResponse(Response.Status.OK, halRepresentation);
    }

    protected static Response buildHalResponse(Response.Status status, HalRepresentation halRepresentation) {
        return Response.status(status).entity(halRepresentation.toString()).build();
    }
}
