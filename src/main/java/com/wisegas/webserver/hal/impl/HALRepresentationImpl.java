package com.wisegas.webserver.hal.impl;

import com.wisegas.webserver.hal.api.HALLink;
import com.wisegas.webserver.hal.api.HALRepresentation;
import com.theoryinpractise.halbuilder.api.*;

import java.io.Writer;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HALRepresentationImpl implements HALRepresentation, Representation {

   private final Representation delegate;

   public HALRepresentationImpl(Representation delegate) {
      this.delegate = delegate;
   }

   public HALRepresentation withLinks(Iterable<HALLink> halLinks) {
      for (HALLink halLink : halLinks) {
         withLink(halLink);
      }
      return this;
   }

   public HALRepresentation withLink(HALLink halLink) {
      delegate.withLink(halLink.getRel(), halLink.getHref());
      return this;
   }

   public HALRepresentation withEmbeddeds(String rel, Iterable<HALRepresentation> halResources) {
      for (HALRepresentation halRepresentation : halResources) {
         withEmbedded(rel, halRepresentation);
      }
      return this;
   }

   public HALRepresentation withEmbedded(String rel, HALRepresentation halRepresentation) {
      if (halRepresentation instanceof ReadableRepresentation) {
         delegate.withRepresentation(rel, (ReadableRepresentation) halRepresentation);
         return this;
      }

      throw new IllegalArgumentException("I don't know how to represent this Embedded HAL Resource: " + halRepresentation);
   }

   @Override
   public String toString() {
      return toString(RepresentationFactory.HAL_JSON);
   }

   @Override
   public HALRepresentationImpl withLink(String rel, String href) {
      delegate.withLink(rel, href);
      return this;
   }

   @Override
   public HALRepresentationImpl withLink(String rel, URI uri) {
      delegate.withLink(rel, uri);
      return this;
   }

   @Override
   public HALRepresentationImpl withLink(String rel, String href, String name, String title, String hreflang, String profile) {
      delegate.withLink(rel, href, name, title, hreflang, profile);
      return this;
   }

   @Override
   public HALRepresentationImpl withProperty(String name, Object value) {
      delegate.withProperty(name, value);
      return this;
   }

   @Override
   public HALRepresentationImpl withBean(Object value) {
      delegate.withBean(value);
      return this;
   }

   @Override
   public HALRepresentationImpl withFields(Object value) {
      delegate.withFields(value);
      return this;
   }

   @Override
   public HALRepresentationImpl withRepresentable(Representable representable) {
      delegate.withRepresentable(representable);
      return this;
   }

   @Override
   public HALRepresentationImpl withFieldBasedRepresentation(String rel, String href, Object o) {
      delegate.withFieldBasedRepresentation(rel, href, o);
      return this;
   }

   @Override
   public HALRepresentationImpl withBeanBasedRepresentation(String rel, String href, Object o) {
      delegate.withBeanBasedRepresentation(rel, href, o);
      return this;
   }

   @Override
   public HALRepresentationImpl withNamespace(String namespace, String href) {
      delegate.withNamespace(namespace, href);
      return this;
   }

   @Override
   public HALRepresentationImpl withRepresentation(String rel, ReadableRepresentation resource) {
      delegate.withRepresentation(rel, resource);
      return this;
   }

   @Override
   public Link getResourceLink() {
      return delegate.getResourceLink();
   }

   @Override
   public Map<String, String> getNamespaces() {
      return delegate.getNamespaces();
   }

   @Override
   public List<Link> getCanonicalLinks() {
      return delegate.getCanonicalLinks();
   }

   @Override
   public List<Link> getLinks() {
      return delegate.getLinks();
   }

   @Override
   public Link getLinkByRel(String rel) {
      return delegate.getLinkByRel(rel);
   }

   @Override
   public List<Link> getLinksByRel(String rel) {
      return delegate.getLinksByRel(rel);
   }

   @Override
   public List<? extends ReadableRepresentation> getResourcesByRel(String rel) {
      return delegate.getResourcesByRel(rel);
   }

   @Override
   public Object getValue(String name) {
      return delegate.getValue(name);
   }

   @Override
   public Object getValue(String name, Object defaultValue) {
      return delegate.getValue(name, defaultValue);
   }

   @Override
   public Map<String, Object> getProperties() {
      return delegate.getProperties();
   }

   @Override
   public boolean hasNullProperties() {
      return delegate.hasNullProperties();
   }

   @Override
   public Collection<Map.Entry<String, ReadableRepresentation>> getResources() {
      return delegate.getResources();
   }

   @Override
   public Map<String, Collection<ReadableRepresentation>> getResourceMap() {
      return delegate.getResourceMap();
   }

   @Override
   public boolean isSatisfiedBy(Contract contract) {
      return delegate.isSatisfiedBy(contract);
   }

   @Override
   public <T> T toClass(Class<T> anInterface) {
      return delegate.toClass(anInterface);
   }

   @Override
   public String toString(String contentType) {
      return delegate.toString(contentType);
   }

   @Override
   @Deprecated
   public String toString(String contentType, Set<URI> flags) {
      return delegate.toString(contentType, flags.toArray(new URI[flags.size()]));
   }

   @Override
   public String toString(String contentType, URI... flags) {
      return delegate.toString(contentType, flags);
   }

   @Override
   public void toString(String contentType, Writer writer) {
      delegate.toString(contentType, writer);
   }

   @Override
   @Deprecated
   public void toString(String contentType, Set<URI> flags, Writer writer) {
      delegate.toString(contentType, writer, new URI[flags.size()]);
   }

   @Override
   public void toString(String contentType, Writer writer, URI... flags) {
      delegate.toString(contentType, writer, flags);
   }
}
