package com.wisegas.grapp.itemmanagement.service_impl.util

import com.wisegas.grapp.itemmanagement.service_impl.value.NacsId
import com.wisegas.grapp.itemmanagement.service_impl.value.NacsItem
import spock.lang.Specification

class NacsItemCsvParserTest extends Specification {

   def "Parsing blank CSV data produces empty List"() {
      expect:
      NacsItemCsvParser.parse("").isEmpty()
   }

   def "Parsing correctly formatted CSV of one Item should produce one NacsItem"() {
      when:
      def result = NacsItemCsvParser.parse("08-00-00,Candy,")

      then:
      result.size() == 1
      result[0] == new NacsItem(new NacsId(8, 0, 0), "Candy", [])
   }

   def "Sub Items are parsed correctly"() {
      when:
      def result = NacsItemCsvParser.parse("22-14-00,Telecommunications Hardware ,\"Cell phones, beepers, accessories\"")

      then:
      result.size() == 1
      result[0] == new NacsItem(new NacsId(22, 14, 0), "Telecommunications Hardware", ["Accessories", "Beepers", "Cell Phones"])
   }

   def "Multiple Items are parsed correctly"() {
      when:
      def result = NacsItemCsvParser.parse("04-07-00,Malt Liquor,\n" +
                                           "04-08-00,Non-alcoholic Beer,\n" +
                                           "04-09-00,Flavored Malt ,\"Hard cider, hard lemonade, malt-based coolers\"")

      then:
      result.size() == 3
      result[0] == new NacsItem(new NacsId(4, 7, 0), "Malt Liquor", [])
      result[1] == new NacsItem(new NacsId(4, 8, 0), "Non-alcoholic Beer", [])
      result[2] == new NacsItem(new NacsId(4, 9, 0), "Flavored Malt", ["Hard Cider", "Hard Lemonade", "Malt-based Coolers"])
   }
}
