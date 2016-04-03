package com.wisegas.grapp.domain.value;

import com.wisegas.common.persistence.jpa.value.EntityID;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class GrappUserIDFUCK extends EntityID {
   @Basic
   private String id;

   public static GrappUserIDFUCK generate() {
      return new GrappUserIDFUCK(generateValue());
   }

   public static GrappUserIDFUCK fromString(String string) {
      return new GrappUserIDFUCK(string);
   }

   protected GrappUserIDFUCK() {

   }

   private GrappUserIDFUCK(String id) {
      this.id = id;
   }

   @Override
   protected Object idHash() {
      return id;
   }
}
