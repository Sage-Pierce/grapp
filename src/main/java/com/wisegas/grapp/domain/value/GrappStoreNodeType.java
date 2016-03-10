package com.wisegas.grapp.domain.value;

public enum GrappStoreNodeType {
   REGULAR,
   ENTRANCE,
   EXIT;

   public static GrappStoreNodeType fromName(String name) {
      return Enum.valueOf(GrappStoreNodeType.class, name);
   }
}
