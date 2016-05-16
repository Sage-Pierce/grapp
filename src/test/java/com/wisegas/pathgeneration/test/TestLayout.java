package com.wisegas.pathgeneration.test;

import com.wisegas.common.lang.spacial.Point;
import com.wisegas.common.lang.spacial.Polygon;

import java.util.Arrays;
import java.util.List;

public final class TestLayout {

   public static Polygon getEnclsoure() {
      return new Polygon(Arrays.asList(new Point(2d, 2d), new Point(10d, 2d), new Point(10d, 7d), new Point(15d, 9d),
                                       new Point(15d, 2d), new Point(25d, 2d), new Point(25d, 24d), new Point(2d, 24d)));
   }

   public static List<Polygon> getPolygons() {
      return Arrays.asList(new Polygon(Arrays.asList(new Point(4d, 9d), new Point(8d, 9d), new Point(8d, 11d), new Point(4d, 11d))),
                           new Polygon(Arrays.asList(new Point(5d, 14d), new Point(7d, 14d), new Point(7d, 22d), new Point(5d, 22d))),
                           new Polygon(Arrays.asList(new Point(10d, 14d), new Point(12d, 14d), new Point(12d, 22d), new Point(10d, 22d))));
   }

   private TestLayout() {

   }
}
