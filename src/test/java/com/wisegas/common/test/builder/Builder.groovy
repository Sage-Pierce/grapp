package com.wisegas.common.test.builder

class Builder {

   static <T> T wrapBuilder(T entity) {
      entity.metaClass.having << { block -> having(entity, block) }
      entity
   }

   static <T> T having(entity, block) {
      entity.with(block)
      entity
   }
}
