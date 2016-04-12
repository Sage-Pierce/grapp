package com.wisegas.grapp.itemmanagement.domain.value;

import com.wisegas.common.persistence.jpa.value.EntityId;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Embeddable
public class GrappItemCode extends EntityId {
   private static final String TYPE_VALUE_DELIMITER = ":";

   @Enumerated(EnumType.STRING)
   private GrappItemCodeType codeType;

   private String codeValue;

   public GrappItemCode(GrappItemCodeType codeType, String codeValue) {
      this.codeType = codeType;
      this.codeValue = codeValue;
   }

   protected GrappItemCode() {

   }

   public static GrappItemCode fromString(String string) {
      String[] split = string.split(TYPE_VALUE_DELIMITER);
      return new GrappItemCode(GrappItemCodeType.valueOf(split[0]), split[1]);
   }

   @Override
   public String toString() {
      return codeType.name() + TYPE_VALUE_DELIMITER + codeValue;
   }

   @Override
   protected Object idHash() {
      return Objects.hash(codeValue, codeType);
   }
}
