package com.wisegas.grapp.domain.value;

import com.wisegas.persistence.jpa.value.EntityID;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class GrappStoreLayoutID extends EntityID {
   @Basic
   private String value;

   public static GrappStoreLayoutID generate() {
      return new GrappStoreLayoutID(generateValue());
   }

   public static GrappStoreLayoutID fromString(String string) {
      return new GrappStoreLayoutID(string);
   }

   protected GrappStoreLayoutID() {

   }

   private GrappStoreLayoutID(String value) {
      this.value = value;
   }

   @Override
   public String getValue() {
      return value;
   }
}
