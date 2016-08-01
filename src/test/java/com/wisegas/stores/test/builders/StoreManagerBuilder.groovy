package com.wisegas.stores.test.builders

import com.wisegas.common.lang.value.Email
import com.wisegas.common.test.util.Builder
import com.wisegas.stores.domain.entity.StoreManager;

class StoreManagerBuilder {

   static unique = 0

   static StoreManager storeManager() {
      unique++
      Builder.wrapBuilder(new StoreManager(
         Email.fromString("test${unique}@email.com")
      )) as StoreManager
   }
}
