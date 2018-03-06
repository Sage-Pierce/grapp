package org.codegas.stores.service_impl.translator;

import java.util.List;
import java.util.stream.Collectors;

import javax.json.JsonObject;
import javax.json.JsonValue;

import org.codegas.common.lang.value.CodeName;
import org.codegas.common.translation.api.Translator;
import org.codegas.common.translation.json.JsonTranslator;
import org.codegas.stores.service.dto.ItemLineageDto;

import static org.codegas.common.translation.json.JsonTranslator.*;

public class ItemLineagesJsonTranslator implements JsonTranslator<List<ItemLineageDto>> {

    @Override
    public List<ItemLineageDto> translate(JsonValue jsonValue) {
        return extractValue("values").andThen(toValueStream())
            .andThen(stream -> stream.map(asObject()))
            .andThen(stream -> stream.map(itemLineageJsonTranslator()))
            .andThen(stream -> stream.collect(Collectors.toList()))
            .apply(jsonValue);
    }

    private Translator<JsonObject, ItemLineageDto> itemLineageJsonTranslator() {
        return jsonObject -> {
            ItemLineageDto itemLineageDto = new ItemLineageDto();
            itemLineageDto.setItem(new CodeName(jsonObject.getString("primaryCode"), jsonObject.getString("name")));
            itemLineageDto.setLineage(toValueStream().andThen(stream -> stream.map(CodeName.translator()))
                .andThen(stream -> stream.collect(Collectors.toList()))
                .apply(jsonObject.get("lineage")));
            return itemLineageDto;
        };
    }
}
