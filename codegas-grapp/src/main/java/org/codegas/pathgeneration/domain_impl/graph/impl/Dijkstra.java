package org.codegas.pathgeneration.domain_impl.graph.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.function.BiFunction;

import org.codegas.pathgeneration.domain_impl.graph.api.GraphPath;
import org.codegas.pathgeneration.domain_impl.graph.api.GraphPoint;

public final class Dijkstra {

    private Dijkstra() {

    }

    public static GraphPath calculatePath(GraphPoint start, GraphPoint finish) {
        GraphPointPath currentPointPath = new GraphPointPath(start, new GraphPath(start));
        Queue<GraphPointPath> pointPathQueue = new PriorityQueue<>(Collections.singleton(currentPointPath));
        Map<GraphPoint, GraphPointPath> pointPaths = new HashMap<>(Collections.singletonMap(start, currentPointPath));
        Set<GraphPoint> visitedPoints = new HashSet<>();
        while (!pointPathQueue.isEmpty() && !Objects.equals(finish, (currentPointPath = pointPathQueue.poll()).getPoint())) {
            visitedPoints.add(currentPointPath.getPoint());
            for (GraphPoint neighbor : currentPointPath.getNeighbors()) {
                if (!visitedPoints.contains(neighbor)) {
                    pointPaths.compute(neighbor, bestPointPathQueueRemapper(pointPathQueue, currentPointPath.add(neighbor)));
                }
            }
        }
        return pointPaths.containsKey(finish) ? pointPaths.get(finish).getPath() : new GraphPath(Collections.emptyList());
    }

    private static BiFunction<GraphPoint, GraphPointPath, GraphPointPath> bestPointPathQueueRemapper(Queue<GraphPointPath> pointPathQueue, GraphPointPath newPointPath) {
        return (graphPoint, oldPointPath) -> {
            if (oldPointPath == null || newPointPath.compareTo(oldPointPath) < 0) {
                pointPathQueue.remove(oldPointPath);
                pointPathQueue.add(newPointPath);
                return newPointPath;
            }
            return oldPointPath;
        };
    }

    private static final class GraphPointPath implements Comparable<GraphPointPath> {

        private final GraphPoint point;

        private final GraphPath path;

        public GraphPointPath(GraphPoint point, GraphPath path) {
            this.point = point;
            this.path = path;
        }

        @Override
        public int compareTo(GraphPointPath graphPointPath) {
            return path.compareTo(graphPointPath.path);
        }

        public GraphPointPath add(GraphPoint graphPoint) {
            return new GraphPointPath(graphPoint, path.add(graphPoint));
        }

        public Collection<GraphPoint> getNeighbors() {
            return point.getNeighbors();
        }

        public GraphPoint getPoint() {
            return point;
        }

        public GraphPath getPath() {
            return path;
        }

    }
}
