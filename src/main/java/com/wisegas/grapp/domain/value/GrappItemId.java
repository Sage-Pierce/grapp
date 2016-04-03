package com.wisegas.grapp.domain.value;

import com.wisegas.common.persistence.jpa.value.EntityIdFuck;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class GrappItemId extends EntityIdFuck {
   @Basic
   private String id;

   public static GrappItemId generate() {
      return new GrappItemId(generateValue());
   }

   public static GrappItemId fromString(String string) {
      return new GrappItemId(string);
   }

   protected GrappItemId() {

   }

   private GrappItemId(String id) {
      this.id = id;
   }

   @Override
   protected Object idHash() {
      return id;
   }
}
