package com.wisegas.grapp.domain.value;

import com.wisegas.persistence.jpa.value.EntityID;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class GrappStoreID extends EntityID {
   @Basic
   private String value;

   public static GrappStoreID generate() {
      return new GrappStoreID(generateValue());
   }

   public static GrappStoreID fromString(String string) {
      return new GrappStoreID(string);
   }

   protected GrappStoreID() {

   }

   private GrappStoreID(String value) {
      this.value = value;
   }

   @Override
   public String getValue() {
      return value;
   }
}
