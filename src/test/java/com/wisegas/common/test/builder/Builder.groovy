package com.wisegas.common.test.builder

class Builder {

   static <T> T wrap(T out) {
      out.metaClass.having << { block -> having(out, block) }
      out
   }

   static <T> T having(out, block) {
      out.with(block)
      out
   }
}
