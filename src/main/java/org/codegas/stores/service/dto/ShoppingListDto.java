package org.codegas.stores.service.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.json.JsonValue;

import org.codegas.common.lang.value.CodeName;
import org.codegas.common.translation.json.JsonTranslator;

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
