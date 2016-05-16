package com.wisegas.pathgeneration.domain_impl.graph.impl

import com.wisegas.common.lang.spacial.Point
import com.wisegas.common.lang.spacial.Polygon
import com.wisegas.pathgeneration.domain_impl.graph.api.Graph
import com.wisegas.pathgeneration.domain_impl.graph.api.GraphPath
import spock.lang.Specification

class PolygonGraphGeneratorTest extends Specification {

   def "An empty Graph can be generated"() {
      given:
      PolygonGraphGenerator graphGenerator = PolygonGraphGenerator.getInstance([])

      when:
      Graph graph = graphGenerator.generate([])

      then:
      graph != null
   }

   def "A Graph with Waypoints can be generated"() {
      given:
      PolygonGraphGenerator graphGenerator = PolygonGraphGenerator.getInstance([])
      Point point = new Point(1d, 1d)

      when:
      Graph graph = graphGenerator.generate([point])

      then:
      GraphPath graphPath = graph.getSingletonPath(point)

      and:
      graphPath.getPoints().size() == 1
      graphPath.getPoints().contains(point)
   }

   def "A Graph with Waypoints can have Paths between the Waypoints generated"() {
      given:
      PolygonGraphGenerator graphGenerator = PolygonGraphGenerator.getInstance([])

      and:
      Point start = new Point(1d, 1d)
      Point finish = new Point(4d, 1d)

      when:
      Graph graph = graphGenerator.generate([start, finish])

      then:
      GraphPath graphPath = graph.getPath(start, finish)

      and:
      graphPath.getPoints().size() == 2
      graphPath.getPoints().containsAll([start, finish])
      graphPath.finishesWith(finish)
   }

   def "A Graph with Waypoints and Polygons can have Paths between the Waypoints and around Polygons generated"() {
      given:
      Polygon polygon = new Polygon([new Point(2d, -2d), new Point(3d, -2d), new Point(3d, 2d), new Point(2d, 2d)])

      and:
      PolygonGraphGenerator graphGenerator = PolygonGraphGenerator.getInstance([polygon])

      and:
      Point start = new Point(1d, 1d)
      Point finish = new Point(4d, 1d)

      when:
      Graph graph = graphGenerator.generate([start, finish])

      then:
      GraphPath graphPath = graph.getPath(start, finish)

      and:
      graphPath.getPoints() == [start, polygon.getVertices()[3], polygon.getVertices()[2], finish]
   }

   def "A Graph with Waypoints and an Enclosure can have Paths between the Waypoints and through the Enclosure generated"() {
      given:
      Polygon enclosure = new Polygon([new Point(1d, 1d), new Point(3d, 1d), new Point(3d, 3d), new Point(4d, 3d),
                                       new Point(4d, 1d), new Point(6d, 1d), new Point(6d, 5d), new Point(1d, 5d)])

      and:
      PolygonGraphGenerator graphGenerator = PolygonGraphGenerator.getInstance(enclosure, [])

      and:
      Point start = new Point(2d, 2d)
      Point finish = new Point(5d, 2d)

      when:
      Graph graph = graphGenerator.generate([start, finish])

      then:
      GraphPath graphPath = graph.getPath(start, finish)

      and:
      graphPath.getPoints() == [start, enclosure.getVertices()[2], enclosure.getVertices()[3], finish]
   }
}
