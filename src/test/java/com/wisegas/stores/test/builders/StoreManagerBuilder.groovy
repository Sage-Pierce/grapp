package com.wisegas.stores.test.builders

import com.wisegas.common.lang.value.Email
import com.wisegas.common.test.entity.EntityBuilder
import com.wisegas.stores.domain.entity.StoreManager;

class StoreManagerBuilder {

   static unique = 0

   static StoreManager storeManager() {
      unique++
      EntityBuilder.wrapBuilder(new StoreManager(
         Email.fromString("test${unique}@email.com")
      )) as StoreManager
   }
}
