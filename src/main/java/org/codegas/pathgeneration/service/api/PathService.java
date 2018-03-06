package org.codegas.pathgeneration.service.api;

import org.codegas.commons.lang.spacial.Point;
import org.codegas.pathgeneration.service.dto.PathDto;
import org.codegas.pathgeneration.service.dto.PathPolygonsDto;
import org.codegas.pathgeneration.service.dto.WaypointsDto;

public interface PathService {

    PathDto generatePath(Point start, Point finish, WaypointsDto waypoints, PathPolygonsDto pathPolygons);
}
