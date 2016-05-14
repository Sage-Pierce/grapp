package com.wisegas.common.lang.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class CollectionUtil {

   public static <T> List<T> concat(Collection<? extends T> collection1, Collection<? extends T> collection2) {
      List<T> concat = new ArrayList<>(collection1.size() + collection2.size());
      concat.addAll(collection1);
      concat.addAll(collection2);
      return concat;
   }

   public static <T> List<List<T>> permute(Collection<T> list) {
      List<List<T>> permutations = new ArrayList<>();
      permute(new ArrayList<>(list), 0, permutations);
      return permutations.isEmpty() ? Collections.singletonList(new ArrayList<>()) : permutations;
   }

   private static <T> void permute(List<T> list, int permutationIndex, Collection<List<T>> permutations) {
      for (int index = permutationIndex; index < list.size(); index++) {
         Collections.swap(list, index, permutationIndex);
         permute(list, permutationIndex + 1, permutations);
         Collections.swap(list, permutationIndex, index);
      }
      if (permutationIndex == list.size() - 1) {
         permutations.add(new ArrayList<>(list));
      }
   }

   private CollectionUtil() {

   }
}
