package com.wisegas.grapp.test.builders

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
