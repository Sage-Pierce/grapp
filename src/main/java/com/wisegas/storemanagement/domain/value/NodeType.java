package com.wisegas.storemanagement.domain.value;

public enum NodeType {
   REGULAR,
   ENTRANCE,
   EXIT;

   public static NodeType fromName(String name) {
      return Enum.valueOf(NodeType.class, name);
   }

   public static NodeType defaultNonSingleton() {
      return REGULAR;
   }

   public boolean isSingleton() {
      return this != REGULAR;
   }
}