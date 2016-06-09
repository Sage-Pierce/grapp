package com.wisegas.stores.service.dto;

import com.wisegas.common.domain.translation.json.JsonTranslator;
import com.wisegas.common.lang.value.CodeName;

import javax.json.JsonValue;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingListDto {
   private static final Translator translator = new Translator();

   private List<CodeName> items;

   public static ShoppingListDto fromString(String json) {
      return translator().translate(json);
   }

   public static JsonTranslator<ShoppingListDto> translator() {
      return translator;
   }

   public List<CodeName> getItems() {
      return items;
   }

   public void setItems(List<CodeName> items) {
      this.items = items;
   }

   private static final class Translator implements JsonTranslator<ShoppingListDto> {

      @Override
      public ShoppingListDto translate(JsonValue jsonValue) {
         return JsonTranslator.extractValue("items")
                              .andThen(JsonTranslator.toValueStream())
                              .andThen(stream -> stream.map(CodeName.translator()))
                              .andThen(stream -> stream.collect(Collectors.toList()))
                              .andThen(this::createDto)
                              .apply(jsonValue);
      }

      private ShoppingListDto createDto(List<CodeName> items) {
         ShoppingListDto shoppingListDto = new ShoppingListDto();
         shoppingListDto.setItems(items);
         return shoppingListDto;
      }
   }
}
