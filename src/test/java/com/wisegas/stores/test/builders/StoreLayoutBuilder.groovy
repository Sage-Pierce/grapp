package com.wisegas.stores.test.builders

import com.wisegas.stores.domain.entity.StoreLayout

class StoreLayoutBuilder {

   static StoreLayout storeLayout() {
      StoreBuilder.store().getStoreLayout()
   }
}
