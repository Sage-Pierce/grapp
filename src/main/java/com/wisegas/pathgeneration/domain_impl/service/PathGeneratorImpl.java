package com.wisegas.pathgeneration.domain_impl.service;

import com.wisegas.common.lang.spacial.Point;
import com.wisegas.common.lang.spacial.Polygon;
import com.wisegas.common.lang.util.CollectionUtil;
import com.wisegas.pathgeneration.domain.service.PathGenerator;
import com.wisegas.pathgeneration.domain.value.Path;
import com.wisegas.pathgeneration.domain.waypoint.WaypointSorter;
import com.wisegas.pathgeneration.domain_impl.graph.api.Graph;
import com.wisegas.pathgeneration.domain_impl.graph.impl.GraphWaypointPathGenerator;
import com.wisegas.pathgeneration.domain_impl.graph.impl.PolygonGraphGenerator;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Arrays;
import java.util.Collection;

@Named
@Singleton
public class PathGeneratorImpl implements PathGenerator {
   private final WaypointSorter waypointSorter;

   @Inject
   public PathGeneratorImpl(WaypointSorter waypointSorter) {
      this.waypointSorter = waypointSorter;
   }

   @Override
   public Path generatePath(Point start, Point finish, Collection<Point> waypoints, Collection<Polygon> polygons, Polygon enclosure) {
      PolygonGraphGenerator graphGenerator = PolygonGraphGenerator.getInstance(enclosure, polygons);
      Graph graph = graphGenerator.generate(CollectionUtil.concat(Arrays.asList(start, finish), waypoints));
      return GraphWaypointPathGenerator.generatePath(graph, start, finish, waypointSorter.sort(start, finish, waypoints));
   }
}
