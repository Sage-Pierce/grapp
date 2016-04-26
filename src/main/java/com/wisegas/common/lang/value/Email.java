package com.wisegas.common.lang.value;

import org.apache.commons.validator.routines.EmailValidator;

public final class Email extends AbstractId {
   private static final EmailValidator EMAIL_VALIDATOR = EmailValidator.getInstance(true, true);

   private String value;

   public static Email fromString(String string) {
      return string == null ? null : new Email(string);
   }

   public Email(String value) {
      this.value = validateEmail(value);
   }

   protected Email() {

   }

   @Override
   public Object idHash() {
      return value;
   }

   public String getValue() {
      return value;
   }

   private static String validateEmail(String value) {
      if (EMAIL_VALIDATOR.isValid(value)) {
         return value;
      }
      else {
         throw new IllegalArgumentException("This is not a valid E-Mail Address: " + value);
      }
   }
}
