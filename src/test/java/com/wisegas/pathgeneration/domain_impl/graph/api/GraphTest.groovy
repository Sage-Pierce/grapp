package com.wisegas.pathgeneration.domain_impl.graph.api

import com.wisegas.common.lang.spacial.Point
import com.wisegas.pathgeneration.domain_impl.graph.impl.Dijkstra
import spock.lang.Specification

import java.util.function.BiFunction

class GraphTest extends Specification {

   def "Path generation within a Graph is inversely memoized"() {
      given:
      int callCount = 0
      BiFunction<GraphPoint, GraphPoint, GraphPath> pathGenerator = new BiFunction<GraphPoint, GraphPoint, GraphPath>() {
         @Override
         GraphPath apply(GraphPoint graphPoint1, GraphPoint graphPoint2) {
            callCount++
            return Dijkstra.calculatePath(graphPoint1, graphPoint2)
         }
      }

      and:
      MutableGraphPoint start = new MutableGraphPoint(new Point(1d, 1d))
      MutableGraphPoint finish = new MutableGraphPoint(new Point(2d, 2d))
      MutableGraphPoint.link(start, finish)

      and:
      Graph graph = new Graph(pathGenerator, [start, finish])

      when:
      def result1 = graph.getPath(start.getPoint(), finish.getPoint())

      then:
      result1.getPoints() == [start.getPoint(), finish.getPoint()]

      and:
      callCount == 1

      when:
      def result2 = graph.getPath(finish.getPoint(), start.getPoint())

      then:
      result2.getPoints() == [finish.getPoint(), start.getPoint()]

      and:
      callCount == 1
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
