package com.wisegas.grapp.domain.value;

import com.wisegas.persistence.jpa.value.EntityID;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class GrappStoreNodeID extends EntityID {
   @Basic
   private String value;

   public static GrappStoreNodeID generate() {
      return new GrappStoreNodeID(generateValue());
   }

   public static GrappStoreNodeID fromString(String string) {
      return new GrappStoreNodeID(string);
   }

   protected GrappStoreNodeID() {

   }

   private GrappStoreNodeID(String value) {
      this.value = value;
   }

   @Override
   public String getValue() {
      return value;
   }
}
