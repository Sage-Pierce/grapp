package com.wisegas.persistence.jpa.converter

import com.wisegas.lang.GeoPoint
import com.wisegas.lang.GeoPolygon
import spock.lang.Specification

class GeoPolygonConverterTest extends Specification {

   static geoPolygonConverter = new GeoPolygonConverter()

   def "A GeoPolygon can be converted to a String and back again"() {
      given:
      def geoPolygon = new GeoPolygon([new GeoPoint(0, 0), new GeoPoint(0, 1), new GeoPoint(1, 0)])

      when:
      def string = geoPolygonConverter.convertToDatabaseColumn(geoPolygon)

      then:
      !string.isEmpty()

      when:
      def convertedGeoPolygon = geoPolygonConverter.convertToEntityAttribute(string)

      then:
      convertedGeoPolygon == geoPolygon
   }
}
