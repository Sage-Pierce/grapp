package com.wisegas.storemanagement.test.builders

import com.wisegas.common.test.EntityBuilder
import com.wisegas.storemanagement.domain.entity.StoreManager;

class StoreManagerBuilder {

   static unique = 0

   static StoreManager storeManager() {
      unique++
      EntityBuilder.wrapBuilder(new StoreManager(
         "test${unique}@email.com"
      )) as StoreManager
   }
}