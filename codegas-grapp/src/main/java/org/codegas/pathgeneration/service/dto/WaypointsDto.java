package org.codegas.pathgeneration.service.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.json.JsonValue;

import org.codegas.commons.lang.spacial.Point;
import org.codegas.commons.ende.api.JsonValueDecoder;

public class WaypointsDto {

    private static final Decoder DECODER = new Decoder();

    private List<Point> points;

    public static WaypointsDto fromString(String json) {
        return decoder().decode(json);
    }

    public static JsonValueDecoder<WaypointsDto> decoder() {
        return DECODER;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    private static final class Decoder implements JsonValueDecoder<WaypointsDto> {

        @Override
        public WaypointsDto decode(JsonValue jsonValue) {
            return JsonValueDecoder.extractValue("points")
                .andThen(JsonValueDecoder.toValueStream())
                .andThen(stream -> stream.map(Point.decoder()))
                .andThen(stream -> stream.collect(Collectors.toList()))
                .andThen(this::createDto)
                .apply(jsonValue);
        }

        private WaypointsDto createDto(List<Point> points) {
            WaypointsDto waypointsDto = new WaypointsDto();
            waypointsDto.setPoints(points);
            return waypointsDto;
        }
    }
}
