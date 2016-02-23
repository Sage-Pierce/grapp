package com.wisegas.test.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public final class FileToStringConverter {

   public static String convert(File xmlFile) {
      StringBuilder stringBuilder = new StringBuilder();
      try (BufferedReader bufferedReader = new BufferedReader(new FileReader(xmlFile))) {
         String line;
         while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
         }
      }
      catch (Exception e) {
         System.err.println("Exception reading File: " + xmlFile.getAbsolutePath());
         e.printStackTrace();
      }
      return stringBuilder.toString();
   }

   private FileToStringConverter() {

   }
}
