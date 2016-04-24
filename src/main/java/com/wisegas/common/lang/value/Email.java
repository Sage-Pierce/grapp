package com.wisegas.common.lang.value;

public class Email extends AbstractId {
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
   public Object idHash() {
      return email;
   }
}
