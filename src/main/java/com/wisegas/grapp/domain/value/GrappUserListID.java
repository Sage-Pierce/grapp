package com.wisegas.grapp.domain.value;

import com.wisegas.persistence.jpa.value.EntityID;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class GrappUserListID extends EntityID {
   @Basic
   private String value;

   public static GrappUserListID generate() {
      return new GrappUserListID(generateValue());
   }

   public static GrappUserListID fromString(String string) {
      return new GrappUserListID(string);
   }

   protected GrappUserListID() {

   }

   private GrappUserListID(String value) {
      this.value = value;
   }

   @Override
   public String getValue() {
      return value;
   }
}
