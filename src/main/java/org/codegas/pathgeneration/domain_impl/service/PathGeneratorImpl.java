package org.codegas.pathgeneration.domain_impl.service;

import java.util.Arrays;
import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.commons.lang.collection.CollectionUtil;
import org.codegas.commons.lang.spacial.Point;
import org.codegas.commons.lang.spacial.Polygon;
import org.codegas.pathgeneration.domain.service.PathGenerator;
import org.codegas.pathgeneration.domain.value.Path;
import org.codegas.pathgeneration.domain.waypoint.WaypointSorter;
import org.codegas.pathgeneration.domain_impl.graph.api.Graph;
import org.codegas.pathgeneration.domain_impl.graph.impl.GraphWaypointPathGenerator;
import org.codegas.pathgeneration.domain_impl.graph.impl.PolygonGraphGenerator;

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
