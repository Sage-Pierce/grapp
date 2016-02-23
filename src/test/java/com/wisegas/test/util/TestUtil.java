package com.wisegas.test.util;

import java.io.File;

public final class TestUtil {
   
   public static File getTestFile() {
      String fs = File.separator;
      String testDirectoryPath = TestUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
      return new File(testDirectoryPath+".."+fs+".."+fs+"resources"+fs+"test"+fs+"Test.xml");
   }

   private TestUtil() {

   }
}
