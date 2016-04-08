package com.wisegas.grapp.storemanagement.domain.value;

import com.wisegas.common.persistence.jpa.value.EntityId;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class GrappStoreNodeItemId extends EntityId {
   @Basic
   private String id;

   public static GrappStoreNodeItemId generate() {
      return new GrappStoreNodeItemId(generateValue());
   }

   public static GrappStoreNodeItemId fromString(String string) {
      return new GrappStoreNodeItemId(string);
   }

   protected GrappStoreNodeItemId() {

   }

   private GrappStoreNodeItemId(String id) {
      this.id = id;
   }

   @Override
   protected Object idHash() {
      return id;
   }
}
