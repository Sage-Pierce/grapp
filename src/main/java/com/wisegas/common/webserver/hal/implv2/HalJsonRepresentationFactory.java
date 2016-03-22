package com.wisegas.common.webserver.hal.implv2;

import com.theoryinpractise.halbuilder.json.JsonRepresentationFactory;
import com.wisegas.common.webserver.hal.apiv2.HalLink;
import com.wisegas.common.webserver.hal.apiv2.HalRepresentation;
import com.wisegas.common.webserver.hal.apiv2.HalRepresentationFactory;

public final class HalJsonRepresentationFactory extends JsonRepresentationFactory implements HalRepresentationFactory {

   public HalJsonRepresentationFactory() {
      // Curies
//      withNamespace("base", UriBuilder.fromUri("").scheme("http").host("localhost").port(8008).segment("rest").segment("{rel}").toTemplate());
//      withNamespace("docs", UriBuilder.fromUri("/").segment("docs").segment("{rel}").toTemplate());
      withFlag(JsonRepresentationFactory.PRETTY_PRINT);
      withFlag(JsonRepresentationFactory.COALESCE_ARRAYS);
   }

   public HalRepresentation createFor(Object resource) {
      return new HalRepresentationImpl(newRepresentation()).withBean(resource);
   }

   public HalRepresentation createForLinks(Iterable<HalLink> links) { return new HalRepresentationImpl(newRepresentation()).withLinks(links); }
}
