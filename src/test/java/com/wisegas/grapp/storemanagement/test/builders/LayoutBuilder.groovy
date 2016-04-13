package com.wisegas.grapp.storemanagement.test.builders

import com.wisegas.grapp.storemanagement.domain.entity.Layout

class LayoutBuilder {

   static Layout layout() {
      StoreBuilder.store().getLayout()
   }
}
