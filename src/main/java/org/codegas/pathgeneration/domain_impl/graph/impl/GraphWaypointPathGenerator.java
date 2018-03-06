package org.codegas.pathgeneration.domain_impl.graph.impl;

import java.util.List;
import java.util.Optional;

import org.codegas.common.lang.collection.CollectionUtil;
import org.codegas.common.lang.spacial.Point;
import org.codegas.pathgeneration.domain_impl.graph.api.Graph;
import org.codegas.pathgeneration.domain_impl.graph.api.GraphPath;

public final class GraphWaypointPathGenerator {

    private static final int MAX_PATH_SEGMENT_WAYPOINT_LENGTH = 5;

    private static final int PATH_SEGMENT_WAYPOINT_EXTRACTION_LENGTH = 2; // NOTE: MUST be less than MAX_PATH_SEGMENT_WAYPOINT_LENGTH

    private GraphWaypointPathGenerator() {

    }

    public static GraphPath generatePath(Graph graph, Point start, Point finish, List<Point> sortedWaypoints) {
        GraphPath currentPath = graph.getSingletonPath(start);
        while (!sortedWaypoints.isEmpty() || !currentPath.finishesWith(finish)) {
            int pathSegmentLength = Math.min(MAX_PATH_SEGMENT_WAYPOINT_LENGTH, sortedWaypoints.size());
            Optional<Point> explicitFinish = sortedWaypoints.size() <= MAX_PATH_SEGMENT_WAYPOINT_LENGTH ? Optional.of(finish) : Optional.empty();
            GraphPath pathSegment = generatePathSegment(graph, currentPath.getFinish(), explicitFinish, sortedWaypoints.subList(0, pathSegmentLength));
            currentPath = currentPath.link(pathSegment);
            sortedWaypoints.removeAll(pathSegment.getPoints());
        }
        return currentPath;
    }

    private static GraphPath generatePathSegment(Graph graph, Point start, Optional<Point> explicitFinish, List<Point> waypoints) {
        List<List<Point>> permutations = generatePermutations(start, explicitFinish, waypoints);
        WaypointsPath segmentWaypointsPath = permutations.stream().map(permutation -> generateWaypointsPath(graph, permutation)).min(WaypointsPath::compareTo).get();
        return explicitFinish.isPresent() ? segmentWaypointsPath.getGraphPath() : segmentWaypointsPath.headPath(PATH_SEGMENT_WAYPOINT_EXTRACTION_LENGTH);
    }

    private static List<List<Point>> generatePermutations(Point start, Optional<Point> explicitFinish, List<Point> waypoints) {
        List<List<Point>> permutations = CollectionUtil.permute(waypoints);
        permutations.forEach(permutation -> permutation.add(0, start));
        explicitFinish.ifPresent(finish -> permutations.forEach(permutation -> permutation.add(finish)));
        return permutations;
    }

    private static WaypointsPath generateWaypointsPath(Graph graph, List<Point> waypoints) {
        GraphPath graphPath = graph.getSingletonPath(waypoints.get(0));
        for (int i = 1; i < waypoints.size(); i++) {
            graphPath = graphPath.link(graph.getPath(waypoints.get(i - 1), waypoints.get(i)));
        }
        return new WaypointsPath(waypoints, graphPath);
    }

    private static class WaypointsPath implements Comparable<WaypointsPath> {

        private final List<Point> waypoints;

        private final GraphPath graphPath;

        private WaypointsPath(List<Point> waypoints, GraphPath graphPath) {
            this.waypoints = waypoints;
            this.graphPath = graphPath;
        }

        public GraphPath headPath(int toWaypointIndex) {
            return graphPath.headPath(waypoints.get(toWaypointIndex));
        }

        public GraphPath getGraphPath() {
            return graphPath;
        }

        @Override
        public int compareTo(WaypointsPath o) {
            return graphPath.compareTo(o.getGraphPath());
        }
    }
}
