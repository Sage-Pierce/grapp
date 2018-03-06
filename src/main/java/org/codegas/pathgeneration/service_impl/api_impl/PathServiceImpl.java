package org.codegas.pathgeneration.service_impl.api_impl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.common.lang.annotation.ApplicationService;
import org.codegas.common.lang.spacial.Point;
import org.codegas.pathgeneration.domain.service.PathGenerator;
import org.codegas.pathgeneration.service.api.PathService;
import org.codegas.pathgeneration.service.dto.PathDto;
import org.codegas.pathgeneration.service.dto.PathPolygonsDto;
import org.codegas.pathgeneration.service.dto.WaypointsDto;
import org.codegas.pathgeneration.service_impl.factory.PathDtoFactory;

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
