package com.wisegas.pathgeneration.service.dto;

import com.wisegas.common.lang.spacial.Point;

import java.util.List;

public class PathDto {

   private List<Point> points;

   public List<Point> getPoints() {
      return points;
   }

   public void setPoints(List<Point> points) {
      this.points = points;
   }
}
