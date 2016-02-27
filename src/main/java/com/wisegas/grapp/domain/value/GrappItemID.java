package com.wisegas.grapp.domain.value;

import com.wisegas.persistence.jpa.value.EntityID;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class GrappItemID extends EntityID {
   @Basic
   private String id;

   public static GrappItemID generate() {
      return new GrappItemID(generateValue());
   }

   public static GrappItemID fromString(String string) {
      return new GrappItemID(string);
   }

   protected GrappItemID() {

   }

   private GrappItemID(String id) {
      this.id = id;
   }

   @Override
   protected Object idHash() {
      return id;
   }
}
