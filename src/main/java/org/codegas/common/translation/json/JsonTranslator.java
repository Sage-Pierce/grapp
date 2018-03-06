package org.codegas.common.translation.json;

import java.io.StringReader;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

import org.codegas.common.translation.api.Translator;

@FunctionalInterface
public interface JsonTranslator<T> extends Translator<JsonValue, T> {

    static JsonTranslator<JsonValue> extractValue(String key) {
        return jsonValue -> asObject().andThen(jsonObject -> jsonObject.get(key)).apply(jsonValue);
    }

    static JsonTranslator<Stream<JsonValue>> toValueStream() {
        return jsonValue -> StreamSupport.stream(asArray().apply(jsonValue).spliterator(), false);
    }

    static JsonTranslator<JsonObject> asObject() {
        return JsonObject.class::cast;
    }

    static JsonTranslator<JsonArray> asArray() {
        return JsonArray.class::cast;
    }

    default T translate(String json) {
        return json == null ? null : translate(Json.createReader(new StringReader(json)).read());
    }
}
