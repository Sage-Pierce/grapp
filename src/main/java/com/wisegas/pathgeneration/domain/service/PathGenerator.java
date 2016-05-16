package com.wisegas.pathgeneration.domain.service;

import com.wisegas.common.lang.spacial.Point;
import com.wisegas.common.lang.spacial.Polygon;
import com.wisegas.pathgeneration.domain.value.Path;

import java.util.Collection;

public interface PathGenerator {
   Path generatePath(Point start, Point finish, Collection<Point> waypoints, Collection<Polygon> polygons, Polygon enclosure);
}
