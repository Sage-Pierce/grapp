package com.wisegas.grapp.domain.entity;

import com.wisegas.persistence.jpa.converter.GeoPointConverter;
import com.wisegas.persistence.jpa.entity.NamedEntity;
import com.wisegas.grapp.domain.value.GrappStoreNodeID;
import com.wisegas.grapp.domain.value.GrappStoreNodeItem;
import com.wisegas.lang.GeoPoint;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "\"GrappStoreNode\"")
public class GrappStoreNode extends NamedEntity<GrappStoreNodeID> {
   @Embedded
   private GrappStoreNodeID id;

   @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
   private GrappStore grappStore;

   @Convert(converter = GeoPointConverter.class)
   private GeoPoint location;

   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "grappStoreNode", orphanRemoval = true)
   private List<GrappStoreNodeItem> grappStoreNodeItems = new ArrayList<>();

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

   private void setGrappStore(GrappStore grappStore) {
      this.grappStore = grappStore;
   }
}
