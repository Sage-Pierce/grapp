package com.wisegas.grapp.itemmanagement.domain.value;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Embeddable
public class GrappItemCode {
   private static final String TYPE_VALUE_DELIMITER = ":";

   @Enumerated(EnumType.STRING)
   private GrappItemCodeType type;

   private String value;

   public GrappItemCode(GrappItemCodeType type, String value) {
      this.type = type;
      this.value = value;
   }

   protected GrappItemCode() {

   }

   public static GrappItemCode fromString(String string) {
      String[] split = string.split(TYPE_VALUE_DELIMITER);
      return new GrappItemCode(GrappItemCodeType.valueOf(split[0]), split[1]);
   }

   @Override
   public boolean equals(Object o) {
      return o != null && getClass().equals(o.getClass()) && hashCode() == o.hashCode();
   }

   @Override
   public int hashCode() {
      return Objects.hash(value, type);
   }

   @Override
   public String toString() {
      return type.name() + TYPE_VALUE_DELIMITER + value;
   }

   public GrappItemCodeType getType() {
      return type;
   }

   public String getValue() {
      return value;
   }
}
