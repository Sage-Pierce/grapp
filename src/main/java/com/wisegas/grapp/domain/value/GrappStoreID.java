package com.wisegas.grapp.domain.value;

import com.wisegas.common.persistence.jpa.value.EntityID;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class GrappStoreID extends EntityID {
   @Basic
   private String id;

   public static GrappStoreID generate() {
      return new GrappStoreID(generateValue());
   }

   public static GrappStoreID fromString(String string) {
      return new GrappStoreID(string);
   }

   protected GrappStoreID() {

   }

   private GrappStoreID(String id) {
      this.id = id;
   }

   @Override
   protected Object idHash() {
      return id;
   }
}
