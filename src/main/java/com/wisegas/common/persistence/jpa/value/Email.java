package com.wisegas.common.persistence.jpa.value;

import com.wisegas.common.lang.value.AbstractId;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class Email extends AbstractId {
   @Basic
   private String email;

   public static Email fromString(String string) {
      return new Email(string);
   }

   protected Email(String email) {
      this.email = email;
   }

   protected Email() {

   }

   public String getEmail() {
      return email;
   }

   @Override
   public Object idHash() {
      return email;
   }
}
