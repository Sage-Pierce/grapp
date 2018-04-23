package org.codegas.pathgeneration.service.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.json.JsonValue;

import org.codegas.commons.lang.spacial.Polygon;
import org.codegas.commons.ende.json.JsonValueDecoder;

public class PathPolygonsDto {

    private static final JsonDecoder JSON_DECODER = new JsonDecoder();

    private Polygon enclosure;

    private List<Polygon> polygons;

    public static PathPolygonsDto fromString(String json) {
        return jsonDecoder().decode(json);
    }

    public static JsonValueDecoder<PathPolygonsDto> jsonDecoder() {
        return JSON_DECODER;
    }

    public Polygon getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(Polygon enclosure) {
        this.enclosure = enclosure;
    }

    public List<Polygon> getPolygons() {
        return polygons;
    }

    public void setPolygons(List<Polygon> polygons) {
        this.polygons = polygons;
    }

    private static final class JsonDecoder implements JsonValueDecoder<PathPolygonsDto> {

        @Override
        public PathPolygonsDto decode(JsonValue jsonValue) {
            return JsonValueDecoder.asObject()
                .andThen(jsonObject -> createDto(Polygon.jsonDecoder().decode(jsonObject.get("enclosure")),
                    decodePolygonsValue(jsonObject.get("polygons"))))
                .apply(jsonValue);
        }

        private List<Polygon> decodePolygonsValue(JsonValue polygonsValue) {
            return JsonValueDecoder.toValueStream()
                .andThen(stream -> stream.map(polygonValue -> Polygon.jsonDecoder().decode(polygonValue)).collect(Collectors.toList()))
                .apply(polygonsValue);
        }

        private PathPolygonsDto createDto(Polygon enclosure, List<Polygon> polygons) {
            PathPolygonsDto pathPolygonsDto = new PathPolygonsDto();
            pathPolygonsDto.setEnclosure(enclosure);
            pathPolygonsDto.setPolygons(polygons);
            return pathPolygonsDto;
        }
    }
}
