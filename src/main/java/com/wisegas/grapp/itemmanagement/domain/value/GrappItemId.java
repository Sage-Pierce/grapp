package com.wisegas.grapp.itemmanagement.domain.value;

import com.wisegas.common.persistence.jpa.value.EntityId;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import java.util.Base64;

@Embeddable
public class GrappItemId extends EntityId {
   @Basic
   private String name;

   public static GrappItemId fromString(String string) {
      return new GrappItemId(new String(Base64.getDecoder().decode(string)));
   }

   public GrappItemId(String name) {
      this.name = name;
   }

   protected GrappItemId() {

   }

   @Override
   public String toString() {
      return Base64.getEncoder().encodeToString(name.getBytes());
   }

   public String getName() {
      return name;
   }

   @Override
   protected Object idHash() {
      return name;
   }
}
