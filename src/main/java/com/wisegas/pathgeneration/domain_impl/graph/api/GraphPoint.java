package com.wisegas.pathgeneration.domain_impl.graph.api;

import com.wisegas.common.lang.spacial.Point;

import java.util.HashSet;
import java.util.Set;

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
