package com.wisegas.storemanagement.test.builders

import com.wisegas.storemanagement.domain.entity.Layout

class LayoutBuilder {

   static Layout layout() {
      StoreBuilder.store().getLayout()
   }
}
