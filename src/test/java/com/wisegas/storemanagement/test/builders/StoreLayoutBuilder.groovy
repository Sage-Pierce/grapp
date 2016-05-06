package com.wisegas.storemanagement.test.builders

import com.wisegas.storemanagement.domain.entity.StoreLayout

class StoreLayoutBuilder {

   static StoreLayout storeLayout() {
      StoreBuilder.store().getStoreLayout()
   }
}
