package com.wisegas.grapp.storemanagement.domain.value;

import com.wisegas.common.persistence.jpa.value.EntityId;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class GrappStoreId extends EntityId {
   @Basic
   private String id;

   public static GrappStoreId generate() {
      return new GrappStoreId(generateValue());
   }

   public static GrappStoreId fromString(String string) {
      return new GrappStoreId(string);
   }

   protected GrappStoreId() {

   }

   private GrappStoreId(String id) {
      this.id = id;
   }

   @Override
   protected Object idHash() {
      return id;
   }
}
