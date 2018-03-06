package org.codegas.pathgeneration.domain.service;

import java.util.Collection;

import org.codegas.common.lang.spacial.Point;
import org.codegas.common.lang.spacial.Polygon;
import org.codegas.pathgeneration.domain.value.Path;

public interface PathGenerator {

    Path generatePath(Point start, Point finish, Collection<Point> waypoints, Collection<Polygon> polygons, Polygon enclosure);
}
