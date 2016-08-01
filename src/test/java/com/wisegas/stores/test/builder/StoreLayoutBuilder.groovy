package com.wisegas.stores.test.builder

import com.wisegas.stores.domain.entity.StoreLayout

class StoreLayoutBuilder {

   static StoreLayout storeLayout() {
      StoreBuilder.store().getStoreLayout()
   }
}
