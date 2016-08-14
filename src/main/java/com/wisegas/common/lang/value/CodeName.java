package com.wisegas.common.lang.value;

import com.wisegas.common.translation.json.JsonTranslator;

import javax.json.Json;
import javax.json.JsonValue;
import java.util.Objects;

public final class CodeName {
   private static final Translator translator = new Translator();

   private String code;
   private String name;

   public CodeName(String code, String name) {
      this.code = code;
      this.name = name;
   }

   protected CodeName() {

   }

   public static CodeName fromString(String json) {
      return translator().translate(json);
   }

   public static JsonTranslator<CodeName> translator() {
      return translator;
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

   @Override
   public String toString() {
      return createValue().toString();
   }

   public JsonValue createValue() {
      return translator.toValue(this);
   }

   public String getCode() {
      return code;
   }

   public String getName() {
      return name;
   }

   private static final class Translator implements JsonTranslator<CodeName> {

      @Override
      public CodeName translate(JsonValue jsonValue) {
         return JsonTranslator.asObject()
                              .andThen(codeNameObject -> new CodeName(codeNameObject.getString("code"),
                                                                      codeNameObject.getString("name")))
                              .apply(jsonValue);
      }

      protected JsonValue toValue(CodeName codeName) {
         return Json.createObjectBuilder()
                    .add("code", codeName.getCode())
                    .add("name", codeName.getName())
                    .build();
      }
   }
}
