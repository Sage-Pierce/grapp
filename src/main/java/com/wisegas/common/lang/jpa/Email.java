package com.wisegas.common.lang.jpa;

import com.wisegas.common.persistence.jpa.value.EntityId;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class Email extends EntityId {
   @Basic
   private String email;

   public static Email fromString(String string) {
      return new Email(string);
   }

   public Email(String email) {
      this.email = email;
   }

   protected Email() {

   }

   public String getEmail() {
      return email;
   }

   @Override
   protected Object idHash() {
      return email;
   }
}
