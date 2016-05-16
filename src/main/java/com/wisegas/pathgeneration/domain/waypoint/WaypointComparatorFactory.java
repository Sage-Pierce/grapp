package com.wisegas.pathgeneration.domain.waypoint;

import com.wisegas.common.lang.spacial.Point;

import java.util.Comparator;

public interface WaypointComparatorFactory {
   Comparator<Point> createComparator(Point start, Point finish);

   double calculateWeight(int resultSize, int resultIndex);
}
