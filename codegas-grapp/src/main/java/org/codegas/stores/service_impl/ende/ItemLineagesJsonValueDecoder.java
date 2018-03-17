package org.codegas.stores.service_impl.ende;

import java.util.List;
import java.util.stream.Collectors;

import javax.json.JsonObject;
import javax.json.JsonValue;

import org.codegas.commons.lang.value.CodeName;
import org.codegas.commons.ende.api.Decoder;
import org.codegas.commons.ende.api.JsonValueDecoder;
import org.codegas.stores.service.dto.ItemLineageDto;

import static org.codegas.commons.ende.api.JsonValueDecoder.*;

public class ItemLineagesJsonValueDecoder implements JsonValueDecoder<List<ItemLineageDto>> {

    @Override
    public List<ItemLineageDto> decode(JsonValue jsonValue) {
        return extractValue("values").andThen(toValueStream())
            .andThen(stream -> stream.map(asObject()))
            .andThen(stream -> stream.map(itemLineageJsonDecoder()))
            .andThen(stream -> stream.collect(Collectors.toList()))
            .apply(jsonValue);
    }

    private Decoder<JsonObject, ItemLineageDto> itemLineageJsonDecoder() {
        return jsonObject -> {
            ItemLineageDto itemLineageDto = new ItemLineageDto();
            itemLineageDto.setItem(new CodeName(jsonObject.getString("primaryCode"), jsonObject.getString("name")));
            itemLineageDto.setLineage(toValueStream().andThen(stream -> stream.map(CodeName.decoder()))
                .andThen(stream -> stream.collect(Collectors.toList()))
                .apply(jsonObject.get("lineage")));
            return itemLineageDto;
        };
    }
}
