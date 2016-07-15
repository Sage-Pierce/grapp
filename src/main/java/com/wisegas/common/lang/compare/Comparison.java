package com.wisegas.common.lang.compare;

public final class Comparison {

   public static boolean areValuesClose(double value1, double value2, double threshold) {
      return Math.abs(value1 - value2) < threshold;
   }

   private Comparison() {

   }
}
