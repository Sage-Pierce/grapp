package com.wisegas.common.test.entity

class EntityBuilder {

   static def wrapBuilder(entity) {
      entity.metaClass.having << { block -> having(entity, block) }
      entity
   }

   static def having(entity, block) {
      entity.with(block)
      entity
   }
}
