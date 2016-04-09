package com.wisegas.grapp.storemanagement.domain.value;

public enum GrappStoreNodeType {
   REGULAR,
   ENTRANCE,
   EXIT;

   public static GrappStoreNodeType fromName(String name) {
      return Enum.valueOf(GrappStoreNodeType.class, name);
   }

   public static GrappStoreNodeType defaultNonSingleton() {
      return REGULAR;
   }

   public boolean isSingleton() {
      return this != REGULAR;
   }
}