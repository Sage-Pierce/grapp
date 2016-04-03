package com.wisegas.grapp.domain.value;

import com.wisegas.common.persistence.jpa.value.EntityIdFuck;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class GrappStoreNodeId extends EntityIdFuck {
   @Basic
   private String id;

   public static GrappStoreNodeId generate() {
      return new GrappStoreNodeId(generateValue());
   }

   public static GrappStoreNodeId fromString(String string) {
      return new GrappStoreNodeId(string);
   }

   protected GrappStoreNodeId() {

   }

   private GrappStoreNodeId(String id) {
      this.id = id;
   }

   @Override
   protected Object idHash() {
      return id;
   }
}
