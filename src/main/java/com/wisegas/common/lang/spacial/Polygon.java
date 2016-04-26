package com.wisegas.common.lang.spacial;

import com.wisegas.common.lang.value.JsonValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Polygon extends JsonValue {

   private List<Point> vertices;

   public static Polygon fromString(String json) {
      return GSON.fromJson(json, Polygon.class);
   }

   public Polygon(List<Point> vertices) {
      this.vertices = vertices;
   }

   protected Polygon() {

   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }

      if (o == null || !getClass().equals(o.getClass())){
         return false;
      }

      Polygon polygon = (Polygon)o;
      return vertices.size() == polygon.vertices.size() &&
             Collections.indexOfSubList(doubleVertices(), polygon.vertices) >= 0;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      for (Point point : vertices) {
         hash ^= point.hashCode();
      }
      return hash;
   }

   public List<Point> getVertices() {
      return vertices;
   }

   private List<Point> doubleVertices() {
      List<Point> doubledVertices = new ArrayList<>(vertices.size() * 2);
      doubledVertices.addAll(vertices);
      doubledVertices.addAll(vertices);
      return doubledVertices;
   }
}
