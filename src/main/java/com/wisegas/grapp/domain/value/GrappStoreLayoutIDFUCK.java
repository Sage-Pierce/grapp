package com.wisegas.grapp.domain.value;

import com.wisegas.common.persistence.jpa.value.EntityID;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class GrappStoreLayoutIDFUCK extends EntityID {
   @Basic
   private String id;

   public static GrappStoreLayoutIDFUCK generate() {
      return new GrappStoreLayoutIDFUCK(generateValue());
   }

   public static GrappStoreLayoutIDFUCK fromString(String string) {
      return new GrappStoreLayoutIDFUCK(string);
   }

   protected GrappStoreLayoutIDFUCK() {

   }

   private GrappStoreLayoutIDFUCK(String id) {
      this.id = id;
   }

   @Override
   protected Object idHash() {
      return id;
   }
}
