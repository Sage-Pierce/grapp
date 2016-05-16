package com.wisegas.pathgeneration.service.api;

import com.wisegas.common.lang.spacial.Point;
import com.wisegas.pathgeneration.service.dto.PathDto;
import com.wisegas.pathgeneration.service.dto.PathPolygonsDto;
import com.wisegas.pathgeneration.service.dto.WaypointsDto;

public interface PathService {
   PathDto generatePath(Point start, Point finish, WaypointsDto waypoints, PathPolygonsDto pathPolygons);
}
