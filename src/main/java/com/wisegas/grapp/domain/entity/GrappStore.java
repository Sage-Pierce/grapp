package com.wisegas.grapp.domain.entity;

import com.wisegas.persistence.jpa.converter.GeoPointConverter;
import com.wisegas.persistence.jpa.entity.NamedEntity;
import com.wisegas.grapp.domain.value.GrappStoreID;
import com.wisegas.grapp.domain.value.GrappStoreNodeItem;
import com.wisegas.value.GeoPoint;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class GrappStore extends NamedEntity<GrappStoreID> {
   @Embedded
   private GrappStoreID id;

   @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
   private GrappUser owner;

   @Convert(converter = GeoPointConverter.class)
   private GeoPoint location;

   @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "grappStore", orphanRemoval = true)
   private GrappStoreLayout grappStoreLayout = new GrappStoreLayout(this);

   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "grappStore", orphanRemoval = true)
   private List<GrappStoreNode> grappStoreNodes = new ArrayList<>();

   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "grappStore", orphanRemoval = true)
   private List<GrappStoreNodeItem> grappStoreNodeItems = new ArrayList<>();

   public GrappStore(GrappUser owner, String name, GeoPoint location) {
      id = GrappStoreID.generate();
      setOwner(owner);
      setName(name);
      setLocation(location);
   }

   protected GrappStore() {

   }

   @Override
   public GrappStoreID getId() {
      return id;
   }

   public GrappUser getOwner() {
      return owner;
   }

   public GeoPoint getLocation() {
      return location;
   }

   public void setLocation(GeoPoint location) {
      this.location = location;
   }

   public GrappStoreLayout getGrappStoreLayout() {
      return grappStoreLayout;
   }

   public List<GrappStoreNode> getGrappStoreNodes() {
      return grappStoreNodes;
   }

   public GrappStoreNode addNode(GeoPoint location, String name) {
      GrappStoreNode grappStoreNode = new GrappStoreNode(this, location, name);
      grappStoreNodes.add(grappStoreNode);
      return grappStoreNode;
   }

   public List<GrappStoreNodeItem> getGrappStoreNodeItems() {
      return grappStoreNodeItems;
   }

   private void setOwner(GrappUser owner) {
      this.owner = owner;
   }
}
