package org.codegas.pathgeneration.domain_impl.waypoint;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.common.lang.collection.CollectionUtil;
import org.codegas.common.lang.spacial.Point;
import org.codegas.pathgeneration.domain.waypoint.WaypointComparatorFactory;
import org.codegas.pathgeneration.domain.waypoint.WaypointSorter;

import static java.util.stream.Collectors.*;

@Named
@Singleton
public class WaypointSorterImpl implements WaypointSorter {

    private final List<WaypointComparatorFactory> factories;

    @Inject
    public WaypointSorterImpl(List<WaypointComparatorFactory> factories) {
        this.factories = factories;
    }

    @Override
    public List<Point> sort(Point start, Point finish, Collection<Point> waypoints) {
        Map<Point, Double> sortedPoints = factories.stream()
            .flatMap(factory -> createSortedPoints(factory, start, finish, waypoints).stream())
            .collect(groupingBy(SortedPoint::getPoint, collectingAndThen(toList(), this::reduceSortedPoints)));
        return sortedPoints.entrySet().stream()
            .sorted((entry1, entry2) -> entry1.getValue().compareTo(entry2.getValue()))
            .map(Map.Entry::getKey)
            .collect(toList());
    }

    private List<SortedPoint> createSortedPoints(WaypointComparatorFactory comparatorFactory, Point start, Point finish, Collection<Point> waypoints) {
        List<Point> sortedWaypoints = CollectionUtil.sort(waypoints, comparatorFactory.createComparator(start, finish));
        return IntStream.range(0, sortedWaypoints.size())
            .mapToObj(i -> new SortedPoint(sortedWaypoints.get(i), i, comparatorFactory.calculateWeight(sortedWaypoints.size(), i)))
            .collect(Collectors.toList());
    }

    private Double reduceSortedPoints(Collection<SortedPoint> sortedPoints) {
        double totalWeight = sortedPoints.stream().mapToDouble(SortedPoint::getWeight).sum();
        double totalWeightedIndex = sortedPoints.stream().mapToDouble(SortedPoint::getWeightedIndex).sum();
        return totalWeightedIndex / totalWeight;
    }

    private static final class SortedPoint {

        private final Point point;

        private final int index;

        private final double weight;

        public SortedPoint(Point point, int index, double weight) {
            this.point = point;
            this.index = index;
            this.weight = weight;
        }

        public Point getPoint() {
            return point;
        }

        public double getWeightedIndex() {
            return index * weight;
        }

        public double getWeight() {
            return weight;
        }
    }
}
