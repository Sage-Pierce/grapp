package com.wisegas.value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeoPolygon extends JsonValue {

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
   public boolean equals(Object object) {
      if (this == object) {
         return true;
      }
      else if (object != null && getClass().equals(object.getClass())) {
         GeoPolygon otherGeoPolygon = (GeoPolygon)object;
         return vertices.size() == otherGeoPolygon.vertices.size() &&
                Collections.indexOfSubList(doubleVertices(), otherGeoPolygon.vertices) >= 0;
      }
      else {
         return false;
      }
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
