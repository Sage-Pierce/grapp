package com.wisegas.grapp.domain.value;

import com.wisegas.common.persistence.jpa.value.EntityID;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class GrappUserID extends EntityID {
   @Basic
   private String id;

   public static GrappUserID generate() {
      return new GrappUserID(generateValue());
   }

   public static GrappUserID fromString(String string) {
      return new GrappUserID(string);
   }

   protected GrappUserID() {

   }

   private GrappUserID(String id) {
      this.id = id;
   }

   @Override
   protected Object idHash() {
      return id;
   }
}
