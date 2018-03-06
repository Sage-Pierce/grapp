package org.codegas.pathgeneration.domain.waypoint;

import java.util.Collection;
import java.util.List;

import org.codegas.common.lang.spacial.Point;

public interface WaypointSorter {

    List<Point> sort(Point start, Point finish, Collection<Point> waypoints);
}
