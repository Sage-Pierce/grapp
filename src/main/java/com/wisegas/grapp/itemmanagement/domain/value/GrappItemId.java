package com.wisegas.grapp.itemmanagement.domain.value;

import com.wisegas.common.persistence.jpa.value.EntityId;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import java.util.Base64;

@Embeddable
public class GrappItemId extends EntityId {
   @Basic
   private String name;

   public static GrappItemId generate() {
      return new GrappItemId(generateValue());
   }

   public static GrappItemId fromString(String string) {
      return new GrappItemId(new String(Base64.getDecoder().decode(string)));
   }

   public static GrappItemId fromName(String name) {
      return new GrappItemId(name);
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

   private GrappItemId(String name) {
      this.name = name;
   }

   @Override
   protected Object idHash() {
      return name;
   }
}
