package org.codegas.pathgeneration.domain_impl.waypoint;

import java.util.Comparator;

import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.common.lang.spacial.Point;
import org.codegas.pathgeneration.domain.waypoint.WaypointComparatorFactory;

@Named
@Singleton
public class FinishDistanceWaypointComparatorFactory implements WaypointComparatorFactory {

    @Override
    public Comparator<Point> createComparator(Point start, Point finish) {
        return (o1, o2) -> Double.compare(o2.distanceTo(finish), o1.distanceTo(finish));
    }

    @Override
    public double calculateWeight(int resultSize, int resultIndex) {
        return (10d * (resultIndex + 1)) / resultSize;
    }
}
