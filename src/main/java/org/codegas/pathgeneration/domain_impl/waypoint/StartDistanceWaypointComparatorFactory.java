package org.codegas.pathgeneration.domain_impl.waypoint;

import java.util.Comparator;

import org.codegas.common.lang.spacial.Point;
import org.codegas.pathgeneration.domain.waypoint.WaypointComparatorFactory;

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
