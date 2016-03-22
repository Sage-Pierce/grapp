package com.wisegas.common.webserver.hal.impl;

import com.theoryinpractise.halbuilder.json.JsonRepresentationFactory;
import com.wisegas.common.webserver.hal.api.HALLink;
import com.wisegas.common.webserver.hal.api.HALRepresentation;
import com.wisegas.common.webserver.hal.api.HalRepresentationFactory;

public final class HalJsonRepresentationFactory extends JsonRepresentationFactory implements HalRepresentationFactory {

   public HalJsonRepresentationFactory() {
      // Curies
//      withNamespace("base", UriBuilder.fromUri("").scheme("http").host("localhost").port(8008).segment("rest").segment("{rel}").toTemplate());
//      withNamespace("docs", UriBuilder.fromUri("/").segment("docs").segment("{rel}").toTemplate());
      withFlag(JsonRepresentationFactory.PRETTY_PRINT);
      withFlag(JsonRepresentationFactory.COALESCE_ARRAYS);
   }

   public HALRepresentation createFor(Object resource) {
      return new HALRepresentationImpl(newRepresentation()).withBean(resource);
   }

   public HALRepresentation createForLinks(Iterable<HALLink> links) { return new HALRepresentationImpl(newRepresentation()).withLinks(links); }
}
