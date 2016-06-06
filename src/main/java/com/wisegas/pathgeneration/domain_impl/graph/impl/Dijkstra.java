package com.wisegas.pathgeneration.domain_impl.graph.impl;

import com.wisegas.pathgeneration.domain_impl.graph.api.GraphPath;
import com.wisegas.pathgeneration.domain_impl.graph.api.GraphPoint;

import java.util.*;

public final class Dijkstra {

   public static GraphPath calculatePath(GraphPoint start, GraphPoint finish) {
      Set<GraphPoint> visitedPoints = new HashSet<>();
      Map<GraphPoint, GraphPointPath> pointPaths = new HashMap<>();
      Queue<GraphPointPath> pointPathQueue = new PriorityQueue<>();
      GraphPointPath currentPointPath = new GraphPointPath(start, new GraphPath(start));
      pointPaths.put(start, currentPointPath);
      pointPathQueue.add(currentPointPath);
      while (!pointPathQueue.isEmpty() && !Objects.equals(finish, (currentPointPath = pointPathQueue.poll()).getPoint())) {
         visitedPoints.add(currentPointPath.getPoint());
         for (GraphPoint neighbor : currentPointPath.getNeighbors()) {
            if (!visitedPoints.contains(neighbor)) {
               GraphPointPath neighborPointPath = currentPointPath.add(neighbor);
               pointPaths.compute(neighbor, (key, oldPointPath) -> {
                  if (oldPointPath == null || oldPointPath.compareTo(neighborPointPath) > 0) {
                     pointPaths.put(key, neighborPointPath);
                     pointPathQueue.remove(oldPointPath);
                     pointPathQueue.add(neighborPointPath);
                     return neighborPointPath;
                  }
                  return oldPointPath;
               });
            }
         }
      }
      return pointPaths.containsKey(finish) ? pointPaths.get(finish).getPath() : new GraphPath(Collections.emptyList());
   }

   private static final class GraphPointPath implements Comparable<GraphPointPath> {

      private final GraphPoint point;
      private final GraphPath path;

      private GraphPointPath(GraphPoint point, GraphPath path) {
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

   private Dijkstra() {

   }
}
