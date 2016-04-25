package com.wisegas.common.lang.value;

public final class Email extends AbstractId {

   private String value;

   public static Email fromString(String string) {
      return new Email(string);
   }

   public Email(String value) {
      this.value = value;
   }

   protected Email() {

   }

   public String getValue() {
      return value;
   }

   @Override
   public Object idHash() {
      return value;
   }
}
