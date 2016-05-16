package com.wisegas.pathgeneration.domain.waypoint;

import com.wisegas.common.lang.spacial.Point;

import java.util.Collection;
import java.util.List;

public interface WaypointSorter {
   List<Point> sort(Point start, Point finish, Collection<Point> waypoints);
}
