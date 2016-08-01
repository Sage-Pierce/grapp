package com.wisegas.stores.test.builder

import com.wisegas.common.lang.value.Email
import com.wisegas.common.test.builder.Builder
import com.wisegas.stores.domain.entity.StoreManager;

class StoreManagerBuilder {

   static unique = 0

   static StoreManager storeManager() {
      unique++
      Builder.wrapBuilder(new StoreManager(
         Email.fromString("test${unique}@email.com")
      ))
   }
}
