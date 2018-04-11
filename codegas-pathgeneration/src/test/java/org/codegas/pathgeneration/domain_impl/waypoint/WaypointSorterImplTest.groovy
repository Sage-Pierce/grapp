package org.codegas.pathgeneration.domain_impl.waypoint

import org.codegas.commons.lang.spacial.Point
import org.codegas.pathgeneration.domain.waypoint.WaypointSorter
import spock.lang.Specification


class WaypointSorterImplTest extends Specification {

   private WaypointSorter waypointSorter = new WaypointSorterImpl([
           new StartFinishProgressionWaypointComparatorFactory(),
           new StartDistanceWaypointComparatorFactory(),
           new FinishDistanceWaypointComparatorFactory()
   ])

   def "Waypoints are sorted in a logical order"() {
      given:
      Point start = new Point(1d, 0d)
      Point finish = new Point(5d, 0d)

      and:
      Point waypoint1 = new Point(0d, 1d)
      Point waypoint2 = new Point(-2d, 2d)
      Point waypoint3 = new Point(1d, 2d)
      Point waypoint4 = new Point(5d, 2d)
      Point waypoint5 = new Point(8d, 2d)
      Point waypoint6 = new Point(6d, 1d)

      and:
      def waypoints = [waypoint3, waypoint4, waypoint2, waypoint5, waypoint1, waypoint6]

      when:
      def result = waypointSorter.sort(start, finish, waypoints);

      then:
      result == [waypoint1, waypoint2, waypoint3, waypoint4, waypoint5, waypoint6]
   }
}
