package com.wisegas.common.lang.value;

import java.util.Objects;

public final class CodeName extends JsonValue {

   private String code;
   private String name;

   public static CodeName fromString(String json) {
      return GSON.fromJson(json, CodeName.class);
   }

   public CodeName(String code, String name) {
      this.code = code;
      this.name = name;
   }

   protected CodeName() {

   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }

      if (o == null || !getClass().equals(o.getClass())){
         return false;
      }

      CodeName idName = (CodeName)o;
      return Objects.equals(this.code, idName.code) &&
             Objects.equals(this.name, idName.name);
   }

   @Override
   public int hashCode() {
      return Objects.hash(code, name);
   }

   public String getCode() {
      return code;
   }

   public String getName() {
      return name;
   }
}
