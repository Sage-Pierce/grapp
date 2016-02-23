package com.wisegas.grapp.domain.value;

import com.wisegas.persistence.jpa.value.EntityID;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class GrappItemID extends EntityID {
   @Basic
   private String value;

   public static GrappItemID generate() {
      return new GrappItemID(generateValue());
   }

   public static GrappItemID fromString(String string) {
      return new GrappItemID(string);
   }

   protected GrappItemID() {

   }

   private GrappItemID(String value) {
      this.value = value;
   }

   @Override
   public String getValue() {
      return value;
   }
}
