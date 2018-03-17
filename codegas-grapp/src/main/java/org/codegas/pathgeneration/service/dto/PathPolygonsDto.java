package org.codegas.pathgeneration.service.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.json.JsonValue;

import org.codegas.commons.lang.spacial.Polygon;
import org.codegas.commons.ende.api.JsonValueDecoder;

public class PathPolygonsDto {

    private static final Decoder DECODER = new Decoder();

    private Polygon enclosure;

    private List<Polygon> polygons;

    public static PathPolygonsDto fromString(String json) {
        return decoder().decode(json);
    }

    public static JsonValueDecoder<PathPolygonsDto> decoder() {
        return DECODER;
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

    private static final class Decoder implements JsonValueDecoder<PathPolygonsDto> {

        @Override
        public PathPolygonsDto decode(JsonValue jsonValue) {
            return JsonValueDecoder.asObject()
                .andThen(jsonObject -> createDto(Polygon.decoder().decode(jsonObject.get("enclosure")),
                    decodePolygonsValue(jsonObject.get("polygons"))))
                .apply(jsonValue);
        }

        private List<Polygon> decodePolygonsValue(JsonValue polygonsValue) {
            return JsonValueDecoder.toValueStream()
                .andThen(stream -> stream.map(polygonValue -> Polygon.decoder().decode(polygonValue)).collect(Collectors.toList()))
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
