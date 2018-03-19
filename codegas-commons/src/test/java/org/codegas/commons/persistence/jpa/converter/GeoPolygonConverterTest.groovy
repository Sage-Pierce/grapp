package org.codegas.commons.persistence.jpa.converter

import org.codegas.commons.lang.spacial.GeoPoint
import org.codegas.commons.lang.spacial.GeoPolygon
import org.codegas.commons.persistence.jpa.GeoPolygonConverter
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
