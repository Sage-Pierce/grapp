package org.codegas.pathgeneration.domain_impl.graph.api;

import java.util.HashSet;
import java.util.Set;

import org.codegas.common.lang.spacial.Point;

public class GraphPoint {

    protected final Point point;

    protected final Set<GraphPoint> neighbors;

    public GraphPoint(Point point) {
        this(point, new HashSet<>());
    }

    public GraphPoint(Point point, Set<GraphPoint> neighbors) {
        this.point = point;
        this.neighbors = neighbors;
    }

    public Point getPoint() {
        return point;
    }

    public Set<GraphPoint> getNeighbors() {
        return neighbors;
    }
}
