package com.wisegas.grapp.domain.value;

import com.wisegas.persistence.jpa.value.EntityID;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class GrappUserID extends EntityID {
   @Basic
   private String value;

   public static GrappUserID generate() {
      return new GrappUserID(generateValue());
   }

   public static GrappUserID fromString(String string) {
      return new GrappUserID(string);
   }

   protected GrappUserID() {

   }

   private GrappUserID(String idValue) {
      this.value = idValue;
   }

   @Override
   public String getValue() {
      return value;
   }
}
