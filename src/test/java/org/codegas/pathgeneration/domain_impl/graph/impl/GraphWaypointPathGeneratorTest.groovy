package org.codegas.pathgeneration.domain_impl.graph.impl

import org.codegas.common.lang.spacial.Point
import org.codegas.pathgeneration.domain_impl.graph.api.Graph
import org.codegas.pathgeneration.domain_impl.graph.api.GraphPath
import org.codegas.pathgeneration.domain_impl.graph.api.GraphPoint
import org.codegas.pathgeneration.test.TestLayout
import spock.lang.Specification

class GraphWaypointPathGeneratorTest extends Specification {

   private PolygonGraphGenerator graphGenerator

   def setup() {
      graphGenerator = PolygonGraphGenerator.getInstance(TestLayout.getEnclsoure(), TestLayout.getPolygons())
   }

   def "We can create a Path through a Layout which starts and finishes at specific Points and hits waypoints"() {
      given: "A Start and Finish"
      Point start = new Point(7d, 4d)
      Point finish = new Point(17d, 4d)

      and: "Some waypoints"
      def waypoints = [new Point(3d, 18d), new Point(8d, 21d)]

      and: "We create a graph with the Points"
      Graph graph = graphGenerator.generate([start, finish] + waypoints)

      when:
      GraphPath path = GraphWaypointPathGenerator.generatePath(graph, start, finish, waypoints)

      then:
      path.getPoints() == [new Point(7d, 4d), new Point(4d, 9d), new Point(3d, 18d), new Point(5d, 22d),
                           new Point(7d, 22d), new Point(8d, 21d), new Point(10d, 14d), new Point(15d, 9d),
                           new Point(17d, 4d)]
   }

   def "GraphPaths are linked correctly when the waypoint amount exceeds the max path calculation"() {
      given:
      int numWaypoints = 6

      and:
      Point start = new Point(0d, 0d)
      def waypoints = (1..numWaypoints).collect { new Point(it, it) }
      Point finish = new Point(numWaypoints + 1, numWaypoints + 1)

      and:
      def graphPoints = ([start] + waypoints + [finish]).collect { new MutableGraphPoint(it) }
      (0..graphPoints.size() - 2).each { MutableGraphPoint.link(graphPoints[it], graphPoints[it + 1]) }

      and:
      Graph graph = new Graph({gp1, gp2 -> Dijkstra.calculatePath(gp1, gp2)}, graphPoints)

      when:
      GraphPath path = GraphWaypointPathGenerator.generatePath(graph, start, finish, waypoints)

      then:
      path.getPoints() == graphPoints.collect { it.getPoint() }
   }

   private static class MutableGraphPoint extends GraphPoint {

      public MutableGraphPoint(Point point) {
         super(point);
      }

      public static void link(GraphPoint graphPoint1, GraphPoint graphPoint2) {
         graphPoint1.neighbors.add(graphPoint2)
         graphPoint2.neighbors.add(graphPoint1)
      }
   }
}
