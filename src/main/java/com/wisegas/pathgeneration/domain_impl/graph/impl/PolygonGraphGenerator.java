package com.wisegas.pathgeneration.domain_impl.graph.impl;

import com.wisegas.common.lang.collection.CollectionUtil;
import com.wisegas.common.lang.spacial.LineSegment;
import com.wisegas.common.lang.spacial.Point;
import com.wisegas.common.lang.spacial.Polygon;
import com.wisegas.pathgeneration.domain_impl.graph.api.Graph;
import com.wisegas.pathgeneration.domain_impl.graph.api.GraphPoint;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class PolygonGraphGenerator {
   private enum LinkCandidacy { POSITIVE, NEGATIVE, POTENTIAL }

   private final Collection<Polygon> polygons;
   private final Collection<PolygonPoint> polygonPoints;

   public static PolygonGraphGenerator getInstance(Polygon enclosure, Collection<Polygon> polygons) {
      return new PolygonGraphGenerator(CollectionUtil.concat(Collections.singleton(enclosure), polygons));
   }

   public static PolygonGraphGenerator getInstance(Collection<Polygon> polygons) {
      return new PolygonGraphGenerator(polygons);
   }

   private PolygonGraphGenerator(Collection<Polygon> polygons) {
      this.polygons = polygons;
      polygonPoints = polygons.stream().flatMap(polygon -> createPolygonPoints(polygon).stream()).collect(toList());
   }

   public Graph generate(Collection<Point> waypoints) {
      List<GraphGenerationPoint> generationPoints = CollectionUtil.concat(polygonPoints, waypoints.stream().map(GraphGenerationPoint::new).collect(toList()));
      Map<Point, MutableGraphPoint> points = generationPoints.stream().collect(toMap(GraphGenerationPoint::getPoint, graphGenerationPoint -> new MutableGraphPoint(graphGenerationPoint.getPoint())));
      for (int i = 0; i < generationPoints.size() - 1; i++) {
         GraphGenerationPoint currentGenerationPoint = generationPoints.get(i);
         calculateLinkablePoints(currentGenerationPoint, generationPoints.subList(i + 1, generationPoints.size())).forEach(neighbor -> {
            points.get(currentGenerationPoint.getPoint()).link(points.get(neighbor));
            points.get(neighbor).link(points.get(currentGenerationPoint.getPoint()));
         });
      }
      return new Graph(Dijkstra::calculatePath, points.values());
   }

   private List<PolygonPoint> createPolygonPoints(Polygon polygon) {
      return polygon.getVertices().stream().map(vertex -> new PolygonPoint(polygon, vertex)).collect(toList());
   }

   private List<Point> calculateLinkablePoints(GraphGenerationPoint currentGenerationPoint, List<GraphGenerationPoint> linkProposals) {
      return linkProposals.stream().filter(linkProposal -> arePointsLinkable(currentGenerationPoint, linkProposal)).map(GraphGenerationPoint::getPoint).collect(toList());
   }

   private boolean arePointsLinkable(GraphGenerationPoint generationPoint, GraphGenerationPoint linkProposal) {
      LinkCandidacy linkCandidacy = generationPoint.calculateLinkCandidacy(linkProposal);
      return linkCandidacy == LinkCandidacy.POSITIVE ||
             linkCandidacy == LinkCandidacy.POTENTIAL && !doesLineSegmentIntersectPolygons(new LineSegment(generationPoint.getPoint(), linkProposal.getPoint()));
   }

   private boolean doesLineSegmentIntersectPolygons(LineSegment lineSegment) {
      return polygons.stream().anyMatch(polygon -> polygon.isIntersectedBy(lineSegment));
   }

   private static class GraphGenerationPoint {

      protected final Point point;

      public GraphGenerationPoint(Point point) {
         this.point = point;
      }

      public LinkCandidacy calculateLinkCandidacy(GraphGenerationPoint linkProposal) {
         return equals(linkProposal) ? LinkCandidacy.NEGATIVE : LinkCandidacy.POTENTIAL;
      }

      public Point getPoint() {
         return point;
      }
   }

   private static class PolygonPoint extends GraphGenerationPoint {

      private final Polygon polygon;

      private PolygonPoint(Polygon polygon, Point point) {
         super(point);
         this.polygon = polygon;
      }

      @Override
      public LinkCandidacy calculateLinkCandidacy(GraphGenerationPoint linkProposal) {
         if (linkProposal instanceof PolygonPoint) {
            PolygonPoint polygonPointNeighborCandidate = (PolygonPoint) linkProposal;
            if (polygon.equals(polygonPointNeighborCandidate.polygon)) {
               return polygon.areVerticesAdjacent(point, polygonPointNeighborCandidate.point) ? LinkCandidacy.POSITIVE : LinkCandidacy.NEGATIVE;
            }
         }
         return super.calculateLinkCandidacy(linkProposal);
      }
   }

   private static class MutableGraphPoint extends GraphPoint {

      public MutableGraphPoint(Point point) {
         super(point);
      }

      public void link(MutableGraphPoint neighbor) {
         neighbors.add(neighbor);
      }
   }
}
