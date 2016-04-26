package com.wisegas.common.lang.spacial;

import com.wisegas.common.lang.value.JsonValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class GeoPolygon extends JsonValue {

   private List<GeoPoint> vertices;

   public static GeoPolygon fromString(String json) {
      return GSON.fromJson(json, GeoPolygon.class);
   }

   public GeoPolygon(List<GeoPoint> vertices) {
      this.vertices = vertices;
   }

   protected GeoPolygon() {

   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }

      if (o == null || !getClass().equals(o.getClass())){
         return false;
      }

      GeoPolygon geoPolygon = (GeoPolygon)o;
      return vertices.size() == geoPolygon.vertices.size() &&
             Collections.indexOfSubList(doubleVertices(), geoPolygon.vertices) >= 0;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      for (GeoPoint point : vertices) {
         hash ^= point.hashCode();
      }
      return hash;
   }

   public List<GeoPoint> getVertices() {
      return vertices;
   }

   private List<GeoPoint> doubleVertices() {
      List<GeoPoint> doubledVertices = new ArrayList<>(vertices.size() * 2);
      doubledVertices.addAll(vertices);
      doubledVertices.addAll(vertices);
      return doubledVertices;
   }
}
