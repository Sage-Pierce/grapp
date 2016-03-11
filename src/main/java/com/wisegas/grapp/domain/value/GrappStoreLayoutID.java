package com.wisegas.grapp.domain.value;

import com.wisegas.common.persistence.jpa.value.EntityID;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class GrappStoreLayoutID extends EntityID {
   @Basic
   private String id;

   public static GrappStoreLayoutID generate() {
      return new GrappStoreLayoutID(generateValue());
   }

   public static GrappStoreLayoutID fromString(String string) {
      return new GrappStoreLayoutID(string);
   }

   protected GrappStoreLayoutID() {

   }

   private GrappStoreLayoutID(String id) {
      this.id = id;
   }

   @Override
   protected Object idHash() {
      return id;
   }
}
