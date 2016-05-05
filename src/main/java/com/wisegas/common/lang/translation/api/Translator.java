package com.wisegas.common.lang.translation.api;

import java.util.function.Function;

@FunctionalInterface
public interface Translator<T, U> extends Function<T, U> {

   default U apply(T t) {
      return translate(t);
   }

   U translate(T t);
}
