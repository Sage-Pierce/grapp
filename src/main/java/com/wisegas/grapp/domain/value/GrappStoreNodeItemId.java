package com.wisegas.grapp.domain.value;

import com.wisegas.common.persistence.jpa.value.EntityID;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class GrappStoreNodeItemID extends EntityID {
   @Basic
   private String id;

   public static GrappStoreNodeItemID generate() {
      return new GrappStoreNodeItemID(generateValue());
   }

   public static GrappStoreNodeItemID fromString(String string) {
      return new GrappStoreNodeItemID(string);
   }

   protected GrappStoreNodeItemID() {

   }

   private GrappStoreNodeItemID(String id) {
      this.id = id;
   }

   @Override
   protected Object idHash() {
      return id;
   }
}
