package com.wisegas.grapp.domain.value;

import com.wisegas.common.persistence.jpa.value.EntityID;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class GrappStoreIDFUCK extends EntityID {
   @Basic
   private String id;

   public static GrappStoreIDFUCK generate() {
      return new GrappStoreIDFUCK(generateValue());
   }

   public static GrappStoreIDFUCK fromString(String string) {
      return new GrappStoreIDFUCK(string);
   }

   protected GrappStoreIDFUCK() {

   }

   private GrappStoreIDFUCK(String id) {
      this.id = id;
   }

   @Override
   protected Object idHash() {
      return id;
   }
}
