package com.wisegas.stores.test.builder

import com.wisegas.common.lang.value.Email
import com.wisegas.stores.domain.entity.StoreManager;

class StoreManagerBuilder {

   static unique = 0

   static StoreManager storeManager() {
      unique++
      new StoreManager(
         Email.fromString("test${unique}@email.com")
      )
   }
}
