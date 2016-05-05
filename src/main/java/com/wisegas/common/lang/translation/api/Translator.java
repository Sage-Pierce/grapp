package com.wisegas.common.lang.translation.api;

import java.util.function.Function;

@FunctionalInterface
public interface Translator<T, R> extends Function<T, R> {

   default <V> Translator<T, V> andThen(Function<? super R, ? extends V> after) {
      return (T t) -> after.apply(apply(t));
   }

   default R applyNullSafe(T t) {
      return t == null ? null : apply(t);
   }

   default R apply(T t) {
      return translate(t);
   }

   R translate(T t);
}
