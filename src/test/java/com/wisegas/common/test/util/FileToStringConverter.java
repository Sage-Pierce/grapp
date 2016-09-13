package com.wisegas.common.test.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public final class FileToStringConverter {

   private FileToStringConverter() {

   }

   public static String convert(File file) {
      StringBuilder stringBuilder = new StringBuilder();
      try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
         for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
            stringBuilder.append(line);
         }
      }
      catch (Exception e) {
         throw new RuntimeException("Exception reading File: " + file.getAbsolutePath(), e);
      }
      return stringBuilder.toString();
   }
}
