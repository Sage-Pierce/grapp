package com.wisegas.pathgeneration.domain_impl.graph.api;

import com.wisegas.common.lang.collection.CollectionUtil;
import com.wisegas.common.lang.function.Invertible;
import com.wisegas.common.lang.spacial.Point;
import com.wisegas.pathgeneration.domain.value.Path;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GraphPath implements Path, Invertible<GraphPath>, Comparable<GraphPath> {

   private final List<GraphPoint> graphPoints;
   private Double length;

   public GraphPath(GraphPoint point) {
      this(Collections.singletonList(point));
   }

   public GraphPath(List<GraphPoint> graphPoints) {
      this.graphPoints = graphPoints;
   }

   private GraphPath(List<GraphPoint> graphPoints, double length) {
      this.graphPoints = graphPoints;
      this.length = length;
   }

   @Override
   public GraphPath invert() {
      return new GraphPath(CollectionUtil.reverse(graphPoints), getLength());
   }

   @Override
   public int compareTo(GraphPath o) {
      return Double.compare(getLength(), o.getLength());
   }

   public GraphPath headPath(Point toPoint) {
      List<GraphPoint> headPoints = new ArrayList<>();
      while (graphPoints.size() > headPoints.size()) {
         headPoints.add(graphPoints.get(headPoints.size()));
         if (toPoint.equals(headPoints.get(headPoints.size() - 1).getPoint())) {
            break;
         }
      }
      return new GraphPath(headPoints.isEmpty() || !toPoint.equals(headPoints.get(headPoints.size() - 1).getPoint()) ? Collections.emptyList() : headPoints);
   }

   public GraphPath link(GraphPath graphPath) {
      if (graphPoints.isEmpty() || graphPath.graphPoints.isEmpty()) {
         return new GraphPath(CollectionUtil.concat(graphPoints, graphPath.graphPoints), getLength() + graphPath.getLength());
      }
      else {
         GraphPoint middleEndPoint = graphPoints.get(graphPoints.size() - 1);
         GraphPoint middleStartPoint = graphPath.graphPoints.get(0);
         if (!Objects.equals(middleEndPoint.getPoint(), middleStartPoint.getPoint())) {
            throw new IllegalArgumentException(String.format("Cannot link a GraphPath ending with Point %s with another starting with Point %s.", middleEndPoint.toString(), middleStartPoint.toString()));
         }
      }
      return new GraphPath(CollectionUtil.concat(graphPoints.subList(0, graphPoints.size() - 1), graphPath.graphPoints), getLength() + graphPath.getLength());
   }

   public GraphPath add(GraphPoint graphPoint) {
      return new GraphPath(CollectionUtil.concat(graphPoints, Collections.singletonList(graphPoint)));
   }

   public boolean finishesWith(Point point) {
      return getFinish().equals(point);
   }

   public Point getFinish() {
      return graphPoints.get(graphPoints.size() - 1).getPoint();
   }

   @Override
   public List<Point> getPoints() {
      return graphPoints.stream().map(GraphPoint::getPoint).collect(Collectors.toList());
   }

   public double getLength() {
      return length == null ? (length = calculateLength()) : length;
   }

   private double calculateLength() {
      double length = 0d;
      for (int i = 1; i < graphPoints.size(); i++) {
         length += graphPoints.get(i - 1).getPoint().distanceTo(graphPoints.get(i).getPoint());
      }
      return length;
   }
}
