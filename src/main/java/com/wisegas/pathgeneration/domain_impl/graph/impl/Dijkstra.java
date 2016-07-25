package com.wisegas.pathgeneration.domain_impl.graph.impl;

import com.wisegas.pathgeneration.domain_impl.graph.api.GraphPath;
import com.wisegas.pathgeneration.domain_impl.graph.api.GraphPoint;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.function.BiFunction;

public final class Dijkstra {

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

      private GraphPointPath(GraphPoint point, GraphPath path) {
         this.point = point;
         this.path = path;
      }

      @Override
      public int compareTo(@Nonnull GraphPointPath graphPointPath) {
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
