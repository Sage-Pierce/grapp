package com.wisegas.pathgeneration.domain_impl.graph.impl

import com.wisegas.common.lang.spacial.Point
import com.wisegas.pathgeneration.domain_impl.graph.api.GraphPoint
import spock.lang.Specification

class DijkstraTest extends Specification {

   def "The Shortest Path between two Graph Points can be found"() {
      given:
      GraphPoint start = new MutableGraphPoint(new Point(1d, 3d))
      GraphPoint finish = new MutableGraphPoint(new Point(8d, 3d))

      and:
      GraphPoint node25 = new MutableGraphPoint(new Point(2d, 5d))
      GraphPoint node45 = new MutableGraphPoint(new Point(4d, 5d))
      GraphPoint node65 = new MutableGraphPoint(new Point(6d, 5d))

      GraphPoint node24 = new MutableGraphPoint(new Point(2d, 4d))
      GraphPoint node74 = new MutableGraphPoint(new Point(7d, 4d))

      GraphPoint node22 = new MutableGraphPoint(new Point(2d, 2d))
      GraphPoint node72 = new MutableGraphPoint(new Point(7d, 2d))
      GraphPoint node92 = new MutableGraphPoint(new Point(9d, 2d))
      GraphPoint node93 = new MutableGraphPoint(new Point(9d, 3d))

      GraphPoint node01 = new MutableGraphPoint(new Point(0d, 1d))
      GraphPoint node71 = new MutableGraphPoint(new Point(7d, 1d))

      and:
      MutableGraphPoint.link(start, node25)
      MutableGraphPoint.link(start, node24)
      MutableGraphPoint.link(start, node22)
      MutableGraphPoint.link(start, node01)

      MutableGraphPoint.link(node25, node45)
      MutableGraphPoint.link(node45, node65)
      MutableGraphPoint.link(node65, finish)

      MutableGraphPoint.link(node24, node74)
      MutableGraphPoint.link(node74, node72)

      MutableGraphPoint.link(node22, node72)
      MutableGraphPoint.link(node72, node92)
      MutableGraphPoint.link(node92, node93)
      MutableGraphPoint.link(node93, finish)

      MutableGraphPoint.link(node01, node71)
      MutableGraphPoint.link(node71, finish)

      when:
      def result = Dijkstra.calculatePath(start, finish)

      then:
      result.getPoints().size() == 5
      result.getPoints().containsAll([start, node25, node45, node65, finish].collect { it.getPoint() })
   }

   private static class MutableGraphPoint extends GraphPoint {

      public MutableGraphPoint(Point point) {
         super(point)
      }

      public static void link(GraphPoint point1, GraphPoint point2) {
         point1.neighbors.add(point2)
         point2.neighbors.add(point1)
      }
   }
}
