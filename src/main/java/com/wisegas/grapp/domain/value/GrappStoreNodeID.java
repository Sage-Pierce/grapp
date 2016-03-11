package com.wisegas.grapp.domain.value;

import com.wisegas.common.persistence.jpa.value.EntityID;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class GrappStoreNodeID extends EntityID {
   @Basic
   private String id;

   public static GrappStoreNodeID generate() {
      return new GrappStoreNodeID(generateValue());
   }

   public static GrappStoreNodeID fromString(String string) {
      return new GrappStoreNodeID(string);
   }

   protected GrappStoreNodeID() {

   }

   private GrappStoreNodeID(String id) {
      this.id = id;
   }

   @Override
   protected Object idHash() {
      return id;
   }
}
