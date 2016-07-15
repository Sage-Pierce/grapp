package com.wisegas.pathgeneration.domain_impl.graph.api

import com.wisegas.common.lang.compare.Comparison
import com.wisegas.common.lang.spacial.Point
import spock.lang.Specification

class GraphPathTest extends Specification {

   def "A GraphPath with no Points has a length of '0'"() {
      expect:
      new GraphPath([]).getLength() == 0d
   }

   def "A GraphPath of a single GraphPoint can be created"() {
      given:
      GraphPoint graphPoint = new GraphPoint(new Point(1d, 1d))

      when:
      GraphPath graphPath = new GraphPath(graphPoint)

      then:
      graphPath.getPoints() == [graphPoint.getPoint()]

      and:
      graphPath.getLength() == 0d
   }

   def "It can be determined if a GraphPath ends with a certain point"() {
      given:
      GraphPoint start = new GraphPoint(new Point(1d, 1d))
      GraphPoint finish = new GraphPoint(new Point(2d, 2d))

      when:
      GraphPath graphPath = new GraphPath([start, finish])

      then:
      graphPath.getPoints() == [start.getPoint(), finish.getPoint()]

      and:
      graphPath.finishesWith(finish.getPoint())

      and:
      !graphPath.finishesWith(start.getPoint())
      !graphPath.finishesWith(new Point(3d, 3d))
   }

   def "The 'length' of a GraphPath can be determined"() {
      expect:
      Comparison.areValuesClose(new GraphPath([new GraphPoint(new Point(1d, 1d)), new GraphPoint(new Point(2d, 2d))]).getLength(), 1.414d, 0.001d)
      Comparison.areValuesClose(new GraphPath([new GraphPoint(new Point(1d, 1d)), new GraphPoint(new Point(2d, 1d))]).getLength(), 1d, 0.001d)
   }

   def "A GraphPath can be inverted"() {
      given:
      GraphPoint graphPoint1 = new GraphPoint(new Point(1d, 1d))
      GraphPoint graphPoint2 = new GraphPoint(new Point(2d, 2d))
      GraphPoint graphPoint3 = new GraphPoint(new Point(3d, 3d))

      and:
      GraphPath graphPath = new GraphPath([graphPoint1, graphPoint2, graphPoint3])

      when:
      def result = graphPath.invert()

      then:
      result.getPoints() == [graphPoint3.getPoint(), graphPoint2.getPoint(), graphPoint1.getPoint()]
      result.getLength() == graphPath.getLength()
   }

   def "GraphPaths can be compared by length"() {
      given:
      GraphPath graphPath1 = new GraphPath([new GraphPoint(new Point(1d, 1d)), new GraphPoint(new Point(2d, 2d))])
      GraphPath graphPath2 = new GraphPath([new GraphPoint(new Point(1d, 1d)), new GraphPoint(new Point(3d, 3d))])
      GraphPath graphPath3 = new GraphPath([new GraphPoint(new Point(2d, 2d)), new GraphPoint(new Point(3d, 3d))])

      expect:
      graphPath1.compareTo(graphPath2) < 0
      graphPath2.compareTo(graphPath1) > 0

      and:
      graphPath1.compareTo(graphPath3) == 0
   }

   def "A GraphPoint can be added to a GraphPath"() {
      given:
      GraphPoint start = new GraphPoint(new Point(1d, 1d))
      GraphPoint finish = new GraphPoint(new Point(2d, 2d))

      and:
      GraphPath initialPath = new GraphPath([start, finish])

      and:
      GraphPoint newFinish = new GraphPoint(new Point(3d, 3d))

      when:
      GraphPath resultPath = initialPath.add(newFinish)

      then:
      resultPath != initialPath
      resultPath.getLength() > initialPath.getLength()
      resultPath.finishesWith(newFinish.getPoint())
   }

   def "The head of a Path to a certain Point can be determined"() {
      given:
      GraphPoint point1 = new GraphPoint(new Point(1d, 1d))
      GraphPoint point2 = new GraphPoint(new Point(2d, 2d))
      GraphPoint point3 = new GraphPoint(new Point(3d, 3d))

      and:
      GraphPath graphPath = new GraphPath([point1, point2, point3])

      expect:
      graphPath.headPath(point1.getPoint()).getPoints() == [point1.getPoint()]
      graphPath.headPath(point3.getPoint()).getPoints() == [point1.getPoint(), point2.getPoint(), point3.getPoint()]
      graphPath.headPath(new Point(4d, 4d)).getPoints() == []
   }

   def "Empty GraphPaths can be 'linked'"() {
      given:
      Point point = new Point(1d, 1d)

      and:
      GraphPath emptyPath = new GraphPath([])
      GraphPath singletonPath = new GraphPath(new GraphPoint(point))

      expect:
      emptyPath.link(emptyPath).getPoints() == []
      emptyPath.link(singletonPath).getPoints() == [point]
      singletonPath.link(emptyPath).getPoints() == [point]
   }

   def "GraphPaths 'A' and 'B' can be 'linked' if 'A' ends with the start of 'B'"() {
      given:
      Point point1 = new Point(1d, 1d)
      Point point2 = new Point(2d, 2d)
      Point point3 = new Point(3d, 3d)

      and:
      GraphPath graphPathA = new GraphPath([new GraphPoint(point1), new GraphPoint(point2)])
      GraphPath graphPathB = new GraphPath([new GraphPoint(point2), new GraphPoint(point3)])

      when:
      GraphPath graphPathAB = graphPathA.link(graphPathB)

      then:
      graphPathAB.getPoints() == [point1, point2, point3]

      when:
      graphPathB.link(graphPathA)

      then:
      thrown(IllegalArgumentException)
   }
}
