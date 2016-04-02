package com.wisegas.grapp.domain.value;

public enum GrappItemCodeType {
   NACS("%02d-%02d-%02d");

   private final String valueFormat;

   public String getValueFormat() {
      return valueFormat;
   }

   GrappItemCodeType(String valueFormat) {
      this.valueFormat = valueFormat;
   }
}
