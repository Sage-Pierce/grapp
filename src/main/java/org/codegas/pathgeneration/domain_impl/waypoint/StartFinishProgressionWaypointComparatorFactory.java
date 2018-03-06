package org.codegas.pathgeneration.domain_impl.waypoint;

import java.util.Comparator;

import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.common.lang.spacial.Point;
import org.codegas.pathgeneration.domain.waypoint.WaypointComparatorFactory;

@Named
@Singleton
public class StartFinishProgressionWaypointComparatorFactory implements WaypointComparatorFactory {

    @Override
    public Comparator<Point> createComparator(Point start, Point finish) {
        return (o1, o2) -> Double.compare(Math.pow(start.distanceTo(o1), 2d) - Math.pow(finish.distanceTo(o1), 2d),
            Math.pow(start.distanceTo(o2), 2d) - Math.pow(finish.distanceTo(o2), 2d));
    }

    @Override
    public double calculateWeight(int resultSize, int resultIndex) {
        return 8d;
    }
}
