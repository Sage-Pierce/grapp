package org.codegas.pathgeneration.service.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.json.JsonValue;

import org.codegas.commons.lang.spacial.Polygon;
import org.codegas.commons.translation.json.JsonTranslator;

public class PathPolygonsDto {

    private static final Translator translator = new Translator();

    private Polygon enclosure;

    private List<Polygon> polygons;

    public static PathPolygonsDto fromString(String json) {
        return translator().translate(json);
    }

    public static JsonTranslator<PathPolygonsDto> translator() {
        return translator;
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

    private static final class Translator implements JsonTranslator<PathPolygonsDto> {

        @Override
        public PathPolygonsDto translate(JsonValue jsonValue) {
            return JsonTranslator.asObject()
                .andThen(jsonObject -> createDto(Polygon.translator().translate(jsonObject.get("enclosure")),
                    translatePolygonsValue(jsonObject.get("polygons"))))
                .apply(jsonValue);
        }

        private List<Polygon> translatePolygonsValue(JsonValue polygonsValue) {
            return JsonTranslator.toValueStream()
                .andThen(stream -> stream.map(polygonValue -> Polygon.translator().translate(polygonValue)).collect(Collectors.toList()))
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
