package org.codegas.common.lang.value;

import java.util.Objects;

import javax.json.Json;
import javax.json.JsonValue;

import org.codegas.common.translation.json.JsonTranslator;

public final class IdName {

    private static final Translator translator = new Translator();

    private String id;

    private String name;

    public IdName(String id, String name) {
        this.id = id;
        this.name = name;
    }

    protected IdName() {

    }

    public static IdName fromString(String json) {
        return translator().translate(json);
    }

    public static JsonTranslator<IdName> translator() {
        return translator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || !getClass().equals(o.getClass())) {
            return false;
        }

        IdName idName = (IdName) o;
        return Objects.equals(this.id, idName.id) &&
            Objects.equals(this.name, idName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return createValue().toString();
    }

    public JsonValue createValue() {
        return translator.toValue(this);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private static final class Translator implements JsonTranslator<IdName> {

        @Override
        public IdName translate(JsonValue jsonValue) {
            return JsonTranslator.asObject()
                .andThen(idNameObject -> new IdName(idNameObject.getString("id"), idNameObject.getString("name")))
                .apply(jsonValue);
        }

        protected JsonValue toValue(IdName idName) {
            return Json.createObjectBuilder()
                .add("id", idName.getId())
                .add("name", idName.getName())
                .build();
        }
    }
}
