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

public final class Polygon {
   private static final Translator translator = new Translator();

   private List<Point> vertices;

   public Polygon(List<Point> vertices) {
      this.vertices = vertices;
   }

   protected Polygon() {

   }

   public static Polygon fromString(String json) {
      return translator().translate(json);
   }

   public static JsonTranslator<Polygon> translator() {
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

   @Override
   public String toString() {
      return createValue().toString();
   }

   public JsonValue createValue() {
      return translator.toValue(this);
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

   private static final class Translator implements JsonTranslator<Polygon> {

      @Override
      public Polygon translate(JsonValue jsonValue) {
         return JsonTranslator.extractValue("vertices")
                              .andThen(JsonTranslator.toValueStream())
                              .andThen(stream -> stream.map(pointValue -> Point.translator().translate(pointValue)))
                              .andThen(stream -> new Polygon(stream.collect(Collectors.toList())))
                              .apply(jsonValue);
      }

      protected JsonValue toValue(Polygon polygon) {
         return Json.createObjectBuilder()
                    .add("vertices", createGeoPointArray(polygon.getVertices()))
                    .build();
      }

      private static JsonArray createGeoPointArray(Collection<Point> points) {
         JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
         points.forEach(point -> arrayBuilder.add(point.createValue()));
         return arrayBuilder.build();
      }
   }
}
