package org.codegas.webservice.hal.api;

public interface HalRepresentation {

    HalRepresentation withLinks(Iterable<HalLink> halLinks);

    HalRepresentation withLink(HalLink halLink);

    HalRepresentation withEmbeddeds(String rel, Iterable<HalRepresentation> halResources);

    HalRepresentation withEmbedded(String rel, HalRepresentation halRepresentation);
}
