package com.wisegas.common.lang.spacial;

import com.wisegas.common.domain.translation.json.JsonTranslator;

import javax.json.Json;
import javax.json.JsonValue;
import java.util.Objects;

public final class Point {
   private static final int HASH_PRECISION = 1000000;
   private static final double DOUBLE_EQUALITY_THRESHOLD = .000000000001d;
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

   public static double determinant(Point p, Point q, Point r) {
      return (q.getX() - p.getX()) * (r.getY() - q.getY()) - (q.getY() - p.getY()) * (r.getX() - q.getX());
   }

   public double distanceTo(Point point) {
      return Math.sqrt(Math.pow(point.x - x, 2d) + Math.pow(point.y - y, 2d));
//      return Math.hypot(point.x - x, point.y - y);
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
      return Math.abs(this.x - point.x) <= DOUBLE_EQUALITY_THRESHOLD &&
             Math.abs(this.y - point.y) <= DOUBLE_EQUALITY_THRESHOLD;
   }

   @Override
   public int hashCode() {
      return Objects.hash((int)(x * HASH_PRECISION), (int)(y * HASH_PRECISION));
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
