package com.wisegas.grapp.domain.entity;

import com.wisegas.persistence.jpa.converter.GeoPointConverter;
import com.wisegas.persistence.jpa.entity.NamedEntity;
import com.wisegas.grapp.domain.value.GrappStoreID;
import com.wisegas.grapp.domain.value.GrappStoreNodeItem;
import com.wisegas.lang.GeoPoint;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "\"GrappStore\"")
public class GrappStore extends NamedEntity<GrappStoreID> {
   @EmbeddedId
   private GrappStoreID id;

   @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
   private GrappUser owner;

   @Column(length = 63)
   @Convert(converter = GeoPointConverter.class)
   private GeoPoint location;

   @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "grappStore", orphanRemoval = true)
   private GrappStoreLayout grappStoreLayout = new GrappStoreLayout(this);

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

   private void setOwner(GrappUser owner) {
      this.owner = owner;
   }
}
