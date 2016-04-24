package com.wisegas.shoppinglist.domain.value;

import com.wisegas.common.persistence.jpa.value.EntityId;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import java.util.Base64;

@Embeddable
public class Email extends EntityId {
   @Basic
   private String email;

   public static Email fromString(String string) {
      return new Email(new String(Base64.getDecoder().decode(string)));
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
      return Base64.getEncoder().encodeToString(email.getBytes());
   }
}
