package org.codegas.pathgeneration.domain_impl.graph.api;

import java.util.Collection;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.codegas.commons.lang.function.FunctionUtil;
import org.codegas.commons.lang.spacial.Point;

public class Graph {

    private final BiFunction<GraphPoint, GraphPoint, GraphPath> pathGenerator;

    private final Map<Point, GraphPoint> points;

    public Graph(BiFunction<GraphPoint, GraphPoint, GraphPath> pathGenerator, Collection<? extends GraphPoint> graphPoints) {
        this.pathGenerator = FunctionUtil.inverseMemoize(pathGenerator);
        points = graphPoints.stream().collect(Collectors.toMap(GraphPoint::getPoint, Function.identity()));
    }

    public GraphPath getSingletonPath(Point point) {
        return new GraphPath(points.get(point));
    }

    public GraphPath getPath(Point start, Point finish) {
        return pathGenerator.apply(points.get(start), points.get(finish));
    }
}
