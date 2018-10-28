package org.codegas.stores.service.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.json.JsonValue;

import org.codegas.commons.lang.ende.JsonValueDecoder;
import org.codegas.commons.lang.value.CodeName;

public class ShoppingListDto {

    private static final JsonDecoder JSON_DECODER = new JsonDecoder();

    private List<CodeName> items;

    public static ShoppingListDto fromString(String json) {
        return jsonDecoder().decode(json);
    }

    public static JsonValueDecoder<ShoppingListDto> jsonDecoder() {
        return JSON_DECODER;
    }

    public List<CodeName> getItems() {
        return items;
    }

    public void setItems(List<CodeName> items) {
        this.items = items;
    }

    private static final class JsonDecoder implements JsonValueDecoder<ShoppingListDto> {

        @Override
        public ShoppingListDto decode(JsonValue jsonValue) {
            return JsonValueDecoder.extractValue("items")
                .andThen(JsonValueDecoder.toValueStream())
                .andThen(stream -> stream.map(CodeName.jsonDecoder()))
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
