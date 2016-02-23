package com.wisegas.grapp.domain.entity;

import com.wisegas.persistence.jpa.converter.GeoPointConverter;
import com.wisegas.persistence.jpa.entity.NamedEntity;
import com.wisegas.grapp.domain.value.GrappStoreNodeID;
import com.wisegas.grapp.domain.value.GrappStoreNodeItem;
import com.wisegas.grapp.domain.value.GrappStoreNodeLink;
import com.wisegas.value.GeoPoint;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class GrappStoreNode extends NamedEntity<GrappStoreNodeID> {
   @Embedded
   private GrappStoreNodeID id;

   @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
   private GrappStore grappStore;

   @Convert(converter = GeoPointConverter.class)
   private GeoPoint location;

   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "grappStoreNode", orphanRemoval = true)
   private List<GrappStoreNodeItem> grappStoreNodeItems = new ArrayList<>();

   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "grappStoreNode1", orphanRemoval = true)
   private List<GrappStoreNodeLink> outgoingLinks = new ArrayList<>();

   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "grappStoreNode2", orphanRemoval = true)
   private List<GrappStoreNodeLink> incomingLinks = new ArrayList<>();

   public GrappStoreNode(GrappStore grappStore, GeoPoint location, String name) {
      id = GrappStoreNodeID.generate();
      setGrappStore(grappStore);
      setLocation(location);
      setName(name);
   }

   protected GrappStoreNode() {

   }

   @Override
   public GrappStoreNodeID getId() {
      return id;
   }

   public GrappStore getGrappStore() {
      return grappStore;
   }

   public GeoPoint getLocation() {
      return location;
   }

   public void setLocation(GeoPoint location) {
      this.location = location;
   }

   public List<GrappStoreNodeItem> getGrappStoreNodeItems() {
      return grappStoreNodeItems;
   }

   public List<GrappStoreNode> getLinkedNodes() {
      List<GrappStoreNode> linkedNodes = new ArrayList<>();
      for (GrappStoreNodeLink outgoingConnection : outgoingLinks) {
         linkedNodes.add(outgoingConnection.getGrappStoreNode2());
      }
      for (GrappStoreNodeLink incomingConnection : incomingLinks) {
         linkedNodes.add(incomingConnection.getGrappStoreNode1());
      }
      return Collections.unmodifiableList(linkedNodes);
   }

   public void addLink(GrappStoreNode otherGrappStoreNode) {
      GrappStoreNodeLink grappStoreNodeLink = new GrappStoreNodeLink(this, otherGrappStoreNode);
      outgoingLinks.add(grappStoreNodeLink);
      otherGrappStoreNode.incomingLinks.add(grappStoreNodeLink);
   }

   private void setGrappStore(GrappStore grappStore) {
      this.grappStore = grappStore;
   }
}
