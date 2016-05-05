package com.wisegas.common.lang.translation.json;

import com.wisegas.common.lang.translation.api.Translator;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.io.StringReader;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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
      return translate(Json.createReader(new StringReader(json)).read());
   }
}
