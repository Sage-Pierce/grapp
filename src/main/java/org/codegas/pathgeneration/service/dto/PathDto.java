package org.codegas.pathgeneration.service.dto;

import java.util.List;

import org.codegas.commons.lang.spacial.Point;

public class PathDto {

    private List<Point> points;

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
