package com.wisegas.lang;

import java.util.Objects;

public class GeoPoint extends JsonValue {

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
   public boolean equals(Object object) {
      if (this == object) {
         return true;
      }
      else if (object != null && getClass().equals(object.getClass())) {
         GeoPoint otherGeoPoint = (GeoPoint)object;
         return lat == otherGeoPoint.lat && lng == otherGeoPoint.lng;
      }
      else {
         return false;
      }
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
