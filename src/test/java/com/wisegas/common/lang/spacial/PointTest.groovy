package com.wisegas.common.lang.spacial

import spock.lang.Specification


class PointTest extends Specification {
   private static final DOUBLE_EQUALITY_THRESHOLD = 0.001d

   def "The distance between two points can be determined"() {
      given:
      Point point = new Point(1d, 1d)

      expect:
      Math.abs(expectedDistance - point.distanceTo(otherPoint)) < DOUBLE_EQUALITY_THRESHOLD

      where:
      expectedDistance | otherPoint
      0d               | new Point(1d, 1d)
      1d               | new Point(0d, 1d)
      1d               | new Point(1d, 0d)
      1d               | new Point(1d, 2d)
      1d               | new Point(2d, 1d)
      1.414d           | new Point(0d, 0d)
      1.414d           | new Point(2d, 2d)
   }
}
