package com.wisegas.grapp.domain.value;

import com.wisegas.persistence.jpa.value.EntityID;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class GrappUserListID extends EntityID {
   @Basic
   private String id;

   public static GrappUserListID generate() {
      return new GrappUserListID(generateValue());
   }

   public static GrappUserListID fromString(String string) {
      return new GrappUserListID(string);
   }

   protected GrappUserListID() {

   }

   private GrappUserListID(String id) {
      this.id = id;
   }

   @Override
   protected Object idHash() {
      return id;
   }
}
