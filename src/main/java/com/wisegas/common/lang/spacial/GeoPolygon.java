package com.wisegas.common.lang.spacial;

import com.wisegas.common.lang.translation.json.JsonTranslator;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonValue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class GeoPolygon {
   private static final Translator translator = new Translator();

   private List<GeoPoint> vertices;

   public GeoPolygon(List<GeoPoint> vertices) {
      this.vertices = vertices;
   }

   protected GeoPolygon() {

   }

   public static GeoPolygon fromString(String json) {
      return translator().translate(json);
   }

   public static JsonTranslator<GeoPolygon> translator() {
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

   public String toString() {
      return createValue().toString();
   }

   public JsonValue createValue() {
      return translator.toValue(this);
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

   private static final class Translator implements JsonTranslator<GeoPolygon> {

      @Override
      public GeoPolygon translate(JsonValue jsonValue) {
         return JsonTranslator.extractValue("vertices")
                              .andThen(JsonTranslator.toValueStream())
                              .andThen(stream -> stream.map(geoPointValue -> GeoPoint.translator().translate(geoPointValue)))
                              .andThen(stream -> new GeoPolygon(stream.map(GeoPoint.class::cast).collect(Collectors.toList())))
                              .apply(jsonValue);
      }

      protected JsonValue toValue(GeoPolygon geoPolygon) {
         return Json.createObjectBuilder()
                    .add("vertices", createGeoPointArray(geoPolygon.getVertices()))
                    .build();
      }

      private static JsonArray createGeoPointArray(Collection<GeoPoint> geoPoints) {
         JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
         geoPoints.forEach(geoPoint -> arrayBuilder.add(geoPoint.createValue()));
         return arrayBuilder.build();
      }
   }
}
