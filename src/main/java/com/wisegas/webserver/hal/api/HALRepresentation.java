package com.wisegas.webserver.hal.api;

public interface HALRepresentation {
   HALRepresentation withLinks(Iterable<HALLink> halLinks);

   HALRepresentation withLink(HALLink halLink);

   HALRepresentation withEmbeddeds(String rel, Iterable<HALRepresentation> halResources);

   HALRepresentation withEmbedded(String rel, HALRepresentation halRepresentation);
}
