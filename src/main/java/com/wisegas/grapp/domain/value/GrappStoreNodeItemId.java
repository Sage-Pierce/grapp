package com.wisegas.grapp.domain.value;

import com.wisegas.common.persistence.jpa.value.EntityID;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class GrappStoreNodeItemId extends EntityID {
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
