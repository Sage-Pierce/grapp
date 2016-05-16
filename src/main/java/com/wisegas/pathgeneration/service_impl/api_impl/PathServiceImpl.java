package com.wisegas.pathgeneration.service_impl.api_impl;

import com.wisegas.common.lang.annotation.ApplicationService;
import com.wisegas.common.lang.spacial.Point;
import com.wisegas.pathgeneration.domain.service.PathGenerator;
import com.wisegas.pathgeneration.service.api.PathService;
import com.wisegas.pathgeneration.service.dto.PathDto;
import com.wisegas.pathgeneration.service.dto.PathPolygonsDto;
import com.wisegas.pathgeneration.service.dto.WaypointsDto;
import com.wisegas.pathgeneration.service_impl.factory.PathDtoFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
@ApplicationService
public class PathServiceImpl implements PathService {

   private final PathGenerator pathGenerator;

   @Inject
   public PathServiceImpl(PathGenerator pathGenerator) {
      this.pathGenerator = pathGenerator;
   }

   @Override
   public PathDto generatePath(Point start, Point finish, WaypointsDto waypoints, PathPolygonsDto pathPolygons) {
      return PathDtoFactory.createDto(pathGenerator.generatePath(start, finish, waypoints.getPoints(), pathPolygons.getPolygons(), pathPolygons.getEnclosure()));
   }
}
