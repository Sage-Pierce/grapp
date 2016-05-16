package com.wisegas.pathgeneration.domain_impl.waypoint;

import com.wisegas.common.lang.spacial.Point;
import com.wisegas.pathgeneration.domain.waypoint.WaypointComparatorFactory;

import java.util.Comparator;

public class StartDistanceWaypointComparatorFactory implements WaypointComparatorFactory {

   @Override
   public Comparator<Point> createComparator(Point start, Point finish) {
      return (o1, o2) -> Double.compare(o1.distanceTo(start), o2.distanceTo(start));
   }

   @Override
   public double calculateWeight(int resultSize, int resultIndex) {
      return (10d * (resultSize - resultIndex)) / resultSize;
   }
}
