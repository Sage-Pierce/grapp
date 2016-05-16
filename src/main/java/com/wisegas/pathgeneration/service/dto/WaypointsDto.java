package com.wisegas.pathgeneration.service.dto;

import com.wisegas.common.lang.spacial.Point;
import com.wisegas.common.lang.translation.json.JsonTranslator;

import javax.json.JsonValue;
import java.util.List;
import java.util.stream.Collectors;

public class WaypointsDto {
   private static final Translator translator = new Translator();

   private List<Point> points;

   public static WaypointsDto fromString(String json) {
      return translator().translate(json);
   }

   public static JsonTranslator<WaypointsDto> translator() {
      return translator;
   }

   public List<Point> getPoints() {
      return points;
   }

   public void setPoints(List<Point> points) {
      this.points = points;
   }

   private static final class Translator implements JsonTranslator<WaypointsDto> {

      @Override
      public WaypointsDto translate(JsonValue jsonValue) {
         return JsonTranslator.extractValue("points")
                              .andThen(JsonTranslator.toValueStream())
                              .andThen(stream -> stream.map(Point.translator()))
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
