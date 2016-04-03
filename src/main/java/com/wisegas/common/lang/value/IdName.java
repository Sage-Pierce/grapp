package com.wisegas.common.lang.value;

import java.util.Objects;

public final class IdName extends JsonValue {

   private String id;
   private String name;

   public static IdName fromString(String json) {
      return GSON.fromJson(json, IdName.class);
   }

   public IdName(String id, String name) {
      this.id = id;
      this.name = name;
   }

   protected IdName() {

   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }

      if (o == null || !getClass().equals(o.getClass())){
         return false;
      }

      IdName idName = (IdName)o;
      return Objects.equals(this.id, idName.id) &&
             Objects.equals(this.name, idName.name);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, name);
   }

   public String getId() {
      return id;
   }

   public String getName() {
      return name;
   }
}
