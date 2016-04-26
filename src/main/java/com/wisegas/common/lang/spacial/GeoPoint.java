package com.wisegas.common.lang.spacial;

import com.wisegas.common.lang.value.JsonValue;

import java.util.Objects;

public final class GeoPoint extends JsonValue {

   private double lat;
   private double lng;

   public static GeoPoint fromString(String json) {
      return GSON.fromJson(json, GeoPoint.class);
   }

   public GeoPoint(double lat, double lng) {
      this.lat = lat;
      this.lng = lng;
   }

   protected GeoPoint() {

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

   public double getLat() {
      return lat;
   }

   public double getLng() {
      return lng;
   }
}
