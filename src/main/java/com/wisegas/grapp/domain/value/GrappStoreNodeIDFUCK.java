package com.wisegas.grapp.domain.value;

import com.wisegas.common.persistence.jpa.value.EntityID;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class GrappStoreNodeIDFUCK extends EntityID {
   @Basic
   private String id;

   public static GrappStoreNodeIDFUCK generate() {
      return new GrappStoreNodeIDFUCK(generateValue());
   }

   public static GrappStoreNodeIDFUCK fromString(String string) {
      return new GrappStoreNodeIDFUCK(string);
   }

   protected GrappStoreNodeIDFUCK() {

   }

   private GrappStoreNodeIDFUCK(String id) {
      this.id = id;
   }

   @Override
   protected Object idHash() {
      return id;
   }
}
