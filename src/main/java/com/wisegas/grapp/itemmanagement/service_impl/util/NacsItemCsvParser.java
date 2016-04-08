package com.wisegas.grapp.itemmanagement.service_impl.util;

import com.wisegas.grapp.itemmanagement.service_impl.value.NacsId;
import com.wisegas.grapp.itemmanagement.service_impl.value.NacsItem;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.text.WordUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public final class NacsItemCsvParser {
   private static final String NACS_ID_DELIMITER_REGEX = "(\\s+)?-(\\s+)?";
   private static final String NACS_SUB_ITEM_DELIMITER_REGEX = "(\\s+)?,(\\s+)?";

   public static List<NacsItem> parse(String nacsItemCsvData) {
      try {
         CSVParser parser = CSVParser.parse(nacsItemCsvData, CSVFormat.EXCEL);
         return StreamSupport.stream(parser.spliterator(), false)
                             .map(NacsItemCsvParser::tryToParseNacsItemCsvRecord)
                             .filter(Optional::isPresent)
                             .map(Optional::get)
                             .collect(Collectors.toList());
      }
      catch (Exception e) {
         return Collections.emptyList();
      }
   }

   private static Optional<NacsItem> tryToParseNacsItemCsvRecord(CSVRecord record) {
      try {
         NacsId nacsId = parseNacsItemId(record.get(0));
         String nacsItemName = parseNacsItemName(record.get(1));
         List<String> nacsSubItems = record.size() > 2 ? parseNacsSubItems(record.get(2)) : Collections.emptyList();
         return Optional.of(new NacsItem(nacsId, nacsItemName, nacsSubItems));
      }
      catch (Exception e) {
         return Optional.empty();
      }
   }

   private static NacsId parseNacsItemId(String csvNacsItemId) {
      String[] split = csvNacsItemId.split(NACS_ID_DELIMITER_REGEX);
      return new NacsId(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
   }

   private static String parseNacsItemName(String csvNacsItemName) {
      return WordUtils.capitalize(csvNacsItemName.trim());
   }

   private static List<String> parseNacsSubItems(String csvNacsSubItems) {
      return Arrays.stream(csvNacsSubItems.split(NACS_SUB_ITEM_DELIMITER_REGEX))
                   .map(String::trim)
                   .filter(item -> !item.isEmpty())
                   .map(WordUtils::capitalize)
                   .sorted()
                   .collect(Collectors.toList());
   }

   private NacsItemCsvParser() {

   }
}
