package com.wisegas.stores.domain.entity;

import com.wisegas.common.domain.event.DomainEventPublisher;
import com.wisegas.common.lang.spacial.GeoPoint;
import com.wisegas.common.persistence.jpa.converter.GeoPointConverter;
import com.wisegas.common.persistence.jpa.entity.NamedEntity;
import com.wisegas.stores.domain.event.NodeModifiedEvent;
import com.wisegas.stores.domain.value.Item;
import com.wisegas.stores.domain.value.NodeId;
import com.wisegas.stores.domain.value.NodeType;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Node extends NamedEntity<NodeId> {
   @EmbeddedId
   private NodeId id;

   @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
   private StoreLayout storeLayout;

   @Column(length = 63)
   @Convert(converter = GeoPointConverter.class)
   private GeoPoint location;

   @Enumerated(EnumType.STRING)
   private NodeType type;

   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "node", orphanRemoval = true)
   @MapKey(name = "item")
   private Map<Item, NodeItem> items = new HashMap<>();

   public Node(StoreLayout storeLayout, String name, NodeType type, GeoPoint location) {
      id = NodeId.generate();
      setStoreLayout(storeLayout);
      setName(name);
      setType(type);
      setLocation(location);
   }

   protected Node() {

   }

   @Override
   public NodeId getId() {
      return id;
   }

   public NodeType getType() {
      return type;
   }

   public void setType(NodeType type) {
      if (this.type != type) {
         this.type = type;
         DomainEventPublisher.instance().publish(new NodeModifiedEvent(id.toString()));
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

   public Collection<NodeItem> getItems() {
      return items.values();
   }

   public NodeItem addItem(Item item) {
      return items.computeIfAbsent(item, code -> new NodeItem(this, item));
   }

   public void removeItem(Item item) {
      if (items.containsKey(item)) {
         items.remove(item);
         DomainEventPublisher.instance().publish(new NodeModifiedEvent(id.toString()));
      }
   }

   private void setStoreLayout(StoreLayout storeLayout) {
      this.storeLayout = storeLayout;
   }
}
