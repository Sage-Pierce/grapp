package com.wisegas.grapp.itemmanagement.domain.value;

import com.wisegas.common.persistence.jpa.value.EntityId;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class GrappItemId extends EntityId {
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