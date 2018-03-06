package org.codegas.pathgeneration.domain.waypoint;

import java.util.Comparator;

import org.codegas.commons.lang.spacial.Point;

public interface WaypointComparatorFactory {

    Comparator<Point> createComparator(Point start, Point finish);

    double calculateWeight(int resultSize, int resultIndex);
}
