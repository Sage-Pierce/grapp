package com.wisegas.common.webserver.hal.apiv2;

public interface HalRepresentationFactory {
   HalRepresentation createFor(Object resource);

   HalRepresentation createForLinks(Iterable<HalLink> links);
}
