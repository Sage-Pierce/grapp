package com.wisegas.common.lang.util

import spock.lang.Specification

class CollectionUtilTest extends Specification {

   def "Two Collections can be concatenated"() {
      expect:
      expectedResult == CollectionUtil.concat(collection1, collection2)

      where:
      expectedResult | collection1 | collection2
      []             | []          | []
      [1]            | [1]         | []
      [1]            | []          | [1]
      [1,2]          | [1]         | [2]
   }

   def "A Collection can be sorted in to a new List"() {
      given:
      def original = [1, 6, 2, 5, 4, 3]

      when:
      def result = CollectionUtil.sort(original)

      then:
      result != original

      and:
      result == [1, 2, 3, 4, 5, 6]
   }

   def "Collections can be permuted"() {
      expect:
      expectedResult == CollectionUtil.permute(collection)

      where:
      collection | expectedResult
      []         | [[]]
      [1]        | [[1]]
      [1, 2]     | [[1, 2], [2, 1]]
      [1, 2, 3]  | [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 2, 1], [3, 1, 2]]
   }
}
