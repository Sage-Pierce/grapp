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
   @EmbeddedId
   private GrappStoreNodeID id;

   @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
   private GrappStoreLayout grappStoreLayout;

   @Column(length = 63)
   @Convert(converter = GeoPointConverter.class)
   private GeoPoint location;

   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "grappStoreNode", orphanRemoval = true)
   private List<GrappStoreNodeItem> grappStoreNodeItems = new ArrayList<>();

   public GrappStoreNode(GrappStoreLayout grappStoreLayout, GeoPoint location, String name) {
      id = GrappStoreNodeID.generate();
      setGrappStoreLayout(grappStoreLayout);
      setLocation(location);
      setName(name);
   }

   protected GrappStoreNode() {

   }

   @Override
   public GrappStoreNodeID getId() {
      return id;
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

   private void setGrappStoreLayout(GrappStoreLayout grappStoreLayout) {
      this.grappStoreLayout = grappStoreLayout;
   }
}
