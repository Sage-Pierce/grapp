package com.wisegas.common.lang.spacial;

import com.wisegas.common.domain.translation.json.JsonTranslator;

import javax.json.Json;
import javax.json.JsonValue;
import java.util.Objects;

public final class GeoPoint {
   private static final Translator translator = new Translator();

   private double lat;
   private double lng;

   public GeoPoint(double lat, double lng) {
      this.lat = lat;
      this.lng = lng;
   }

   protected GeoPoint() {

   }

   public static GeoPoint fromString(String json) {
      return translator().translate(json);
   }

   public static JsonTranslator<GeoPoint> translator() {
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

      GeoPoint geoPoint = (GeoPoint)o;
      return this.lat == geoPoint.lat &&
             this.lng == geoPoint.lng;
   }

   @Override
   public int hashCode() {
      return Objects.hash(lat, lng);
   }

   @Override
   public String toString() {
      return createValue().toString();
   }

   public JsonValue createValue() {
      return translator.toValue(this);
   }

   public double getLat() {
      return lat;
   }

   public double getLng() {
      return lng;
   }

   private static final class Translator implements JsonTranslator<GeoPoint> {

      @Override
      public GeoPoint translate(JsonValue jsonValue) {
         return JsonTranslator.asObject()
                              .andThen(geoPointObject -> new GeoPoint(geoPointObject.getJsonNumber("lat").doubleValue(),
                                                                      geoPointObject.getJsonNumber("lng").doubleValue()))
                              .applyNullSafe(jsonValue);
      }

      protected JsonValue toValue(GeoPoint geoPoint) {
         return Json.createObjectBuilder()
                    .add("lat", geoPoint.getLat())
                    .add("lng", geoPoint.getLng())
                    .build();
      }
   }
}
