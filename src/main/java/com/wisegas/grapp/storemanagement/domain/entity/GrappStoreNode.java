package com.wisegas.grapp.storemanagement.domain.entity;

import com.wisegas.common.domain.model.DomainEventPublisher;
import com.wisegas.common.lang.value.GeoPoint;
import com.wisegas.common.persistence.jpa.converter.GeoPointConverter;
import com.wisegas.common.persistence.jpa.entity.NamedEntity;
import com.wisegas.grapp.storemanagement.domain.event.GrappStoreNodeModifiedEvent;
import com.wisegas.grapp.storemanagement.domain.value.GrappStoreNodeId;
import com.wisegas.grapp.storemanagement.domain.value.GrappStoreNodeType;
import com.wisegas.grapp.storemanagement.domain.value.Item;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Entity
public class GrappStoreNode extends NamedEntity<GrappStoreNodeId> {
   @EmbeddedId
   private GrappStoreNodeId id;

   @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
   private GrappStoreLayout grappStoreLayout;

   @Column(length = 63)
   @Convert(converter = GeoPointConverter.class)
   private GeoPoint location;

   @Enumerated(EnumType.STRING)
   private GrappStoreNodeType type;

   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "grappStoreNode", orphanRemoval = true)
   @MapKey(name = "item")
   private Map<Item, GrappStoreNodeItem> items = new HashMap<>();

   public GrappStoreNode(GrappStoreLayout grappStoreLayout, String name, GrappStoreNodeType type, GeoPoint location) {
      id = GrappStoreNodeId.generate();
      setGrappStoreLayout(grappStoreLayout);
      setName(name);
      setType(type);
      setLocation(location);
   }

   protected GrappStoreNode() {

   }

   @Override
   public GrappStoreNodeId getId() {
      return id;
   }

   public GrappStoreNodeType getType() {
      return type;
   }

   public void setType(GrappStoreNodeType type) {
      if (this.type != type) {
         this.type = type;
         DomainEventPublisher.instance().publish(new GrappStoreNodeModifiedEvent(id.toString()));
      }
   }

   public GeoPoint getLocation() {
      return location;
   }

   public void setLocation(GeoPoint location) {
      this.location = location;
   }

   public boolean containsItem(Item item) {
      return items.containsKey(item);
   }

   public Collection<GrappStoreNodeItem> getItems() {
      return items.values();
   }

   public GrappStoreNodeItem addItem(Item item) {
      return items.computeIfAbsent(item, code -> new GrappStoreNodeItem(this, item));
   }

   public void removeItem(Item item) {
      if (items.containsKey(item)) {
         items.remove(item);
         DomainEventPublisher.instance().publish(new GrappStoreNodeModifiedEvent(id.toString()));
      }
   }

   private void setGrappStoreLayout(GrappStoreLayout grappStoreLayout) {
      this.grappStoreLayout = grappStoreLayout;
   }
}
