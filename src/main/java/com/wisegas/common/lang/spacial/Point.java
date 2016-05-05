package com.wisegas.common.lang.spacial;

import com.wisegas.common.lang.translation.json.JsonTranslator;

import javax.json.Json;
import javax.json.JsonValue;
import java.util.Objects;

public final class Point {
   private static final Translator translator = new Translator();

   private double x;
   private double y;

   public Point(double x, double y) {
      this.x = x;
      this.y = y;
   }

   protected Point() {

   }

   public static Point fromString(String json) {
      return translator().translate(json);
   }

   public static JsonTranslator<Point> translator() {
      return translator;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }

      if (o == null || !getClass().equals(o.getClass())){
         return false;
      }

      Point point = (Point)o;
      return this.x == point.x &&
             this.y == point.y;
   }

   @Override
   public int hashCode() {
      return Objects.hash(x, y);
   }

   @Override
   public String toString() {
      return createValue().toString();
   }

   public JsonValue createValue() {
      return translator.toValue(this);
   }

   public double getX() {
      return x;
   }

   public double getY() {
      return y;
   }

   private static final class Translator implements JsonTranslator<Point> {

      @Override
      public Point translate(JsonValue jsonValue) {
         return JsonTranslator.asObject()
                              .andThen(pointObject -> new Point(pointObject.getJsonNumber("x").doubleValue(),
                                                                pointObject.getJsonNumber("y").doubleValue()))
                              .apply(jsonValue);
      }

      protected JsonValue toValue(Point point) {
         return Json.createObjectBuilder()
                    .add("x", point.getX())
                    .add("y", point.getY())
                    .build();
      }
   }
}
