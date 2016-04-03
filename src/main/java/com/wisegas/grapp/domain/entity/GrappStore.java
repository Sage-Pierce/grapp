package com.wisegas.grapp.domain.entity;

import com.wisegas.common.lang.value.GeoPoint;
import com.wisegas.common.persistence.jpa.converter.GeoPointConverter;
import com.wisegas.common.persistence.jpa.entity.NamedEntity;
import com.wisegas.grapp.domain.value.GrappStoreIDFUCK;

import javax.persistence.*;

@Entity
public class GrappStore extends NamedEntity<GrappStoreIDFUCK> {
   @EmbeddedId
   private GrappStoreIDFUCK id;

   @Column(length = 63)
   @Convert(converter = GeoPointConverter.class)
   private GeoPoint location;

   @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "grappStore", orphanRemoval = true)
   private GrappStoreLayout grappStoreLayout = new GrappStoreLayout(this);

   public GrappStore(String name, GeoPoint location) {
      id = GrappStoreIDFUCK.generate();
      setName(name);
      setLocation(location);
   }

   protected GrappStore() {

   }

   @Override
   public GrappStoreIDFUCK getId() {
      return id;
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
}
