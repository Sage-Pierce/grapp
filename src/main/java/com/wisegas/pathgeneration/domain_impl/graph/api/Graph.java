package com.wisegas.pathgeneration.domain_impl.graph.api;

import com.wisegas.common.lang.spacial.Point;

import java.util.Collection;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Graph {

   private final Map<Point, GraphPoint> points;
   private final BiFunction<GraphPoint, GraphPoint, GraphPath> pathGenerator;

   public Graph(BiFunction<GraphPoint, GraphPoint, GraphPath> pathGenerator, Collection<? extends GraphPoint> graphPoints) {
      this.pathGenerator = pathGenerator;
      points = graphPoints.stream().collect(Collectors.toMap(GraphPoint::getPoint, Function.identity()));
   }

   public GraphPath getSingletonPath(Point point) {
      return new GraphPath(points.get(point));
   }

   public GraphPath getPath(Point start, Point finish) {
      return pathGenerator.apply(points.get(start), points.get(finish));
   }
}
