package com.wisegas.common.webserver.hal.impl;

import com.theoryinpractise.halbuilder.json.JsonRepresentationFactory;
import com.wisegas.common.webserver.hal.api.HalLink;
import com.wisegas.common.webserver.hal.api.HalRepresentation;
import com.wisegas.common.webserver.hal.api.HalRepresentationFactory;

import java.io.Serializable;
import java.util.Collection;

public final class HalJsonRepresentationFactory extends JsonRepresentationFactory implements HalRepresentationFactory {

   public HalJsonRepresentationFactory() {
      // Curies
//      withNamespace("base", UriBuilder.fromUri("").scheme("http").host("localhost").port(8008).segment("rest").segment("{rel}").toTemplate());
//      withNamespace("docs", UriBuilder.fromUri("/").segment("docs").segment("{rel}").toTemplate());
      withFlag(JsonRepresentationFactory.PRETTY_PRINT);
      withFlag(JsonRepresentationFactory.COALESCE_ARRAYS);
   }

   public HalRepresentation createFor(Object resource) {
      return new HalJsonRepresentationImpl(newRepresentation()).withBean(wrapResource(resource));
   }

   public HalRepresentation createForLinks(Iterable<HalLink> links) { return new HalJsonRepresentationImpl(newRepresentation()).withLinks(links); }

   private Object wrapResource(Object resource) {
      return resource instanceof Collection ? new CollectionWrapper((Collection)resource) : resource;
   }

   protected static class CollectionWrapper implements Serializable {
      private final Collection values;

      private CollectionWrapper(Collection values) {
         this.values = values;
      }

      public Collection getValues() {
         return values;
      }
   }
}
