package com.wisegas.common.webserver.hal.api;

public interface HalRepresentationFactory {
   HALRepresentation createFor(Object resource);

   HALRepresentation createForLinks(Iterable<HALLink> links);
}
