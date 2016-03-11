package com.wisegas.common.webserver.hal;

import com.wisegas.common.webserver.hal.api.HALLink;
import com.wisegas.common.webserver.hal.api.HALRepresentation;
import com.wisegas.common.webserver.hal.impl.HALRepresentationImpl;
import com.theoryinpractise.halbuilder.api.RepresentationFactory;
import com.theoryinpractise.halbuilder.json.JsonRepresentationFactory;

public final class HALRepresentationFactory extends JsonRepresentationFactory {

   public HALRepresentationFactory() {
      // Curies
//      withNamespace("base", UriBuilder.fromUri("").scheme("http").host("localhost").port(8008).segment("rest").segment("{rel}").toTemplate());
//      withNamespace("docs", UriBuilder.fromUri("/").segment("docs").segment("{rel}").toTemplate());
      withFlag(RepresentationFactory.PRETTY_PRINT);
      withFlag(RepresentationFactory.COALESCE_ARRAYS);
   }

   public HALRepresentation createFor(Object resource) {
      return new HALRepresentationImpl(newRepresentation()).withBean(resource);
   }

   public HALRepresentation createForLinks(Iterable<HALLink> links) { return new HALRepresentationImpl(newRepresentation()).withLinks(links); }
}
