package com.wisegas.grapp.storemanagement.test.builders

import com.wisegas.grapp.storemanagement.domain.entity.Layout

class GrappStoreLayoutBuilder {

   static Layout grappStoreLayout() {
      GrappStoreBuilder.grappStore().getLayout()
   }
}
