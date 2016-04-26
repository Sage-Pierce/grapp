package com.wisegas.common.lang.spacial;

import com.wisegas.common.lang.value.JsonValue;

import java.util.Objects;

public final class Point extends JsonValue {

   private double x;
   private double y;

   public static Point fromString(String json) {
      return GSON.fromJson(json, Point.class);
   }

   public Point(double x, double y) {
      this.x = x;
      this.y = y;
   }

   protected Point() {

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

   public double getX() {
      return x;
   }

   public double getY() {
      return y;
   }
}
